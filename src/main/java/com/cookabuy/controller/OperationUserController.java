package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.DisplayUser;
import com.cookabuy.entity.operation.dto.UpdateUserForm;
import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.*;
import com.cookabuy.service.UserService;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import com.cookabuy.util.selector.Menu2Selector;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2016/12/13
 */
@RestController
@RequestMapping("operate")
@Slf4j
public class OperationUserController {
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private OperationUserRepository operationUserRepository;

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;



//    @RequestMapping("/add_user")
//    public Result addUser(@RequestBody @Valid AddUserForm user, BindingResult bindingResult, Result result) {
//        if(bindingResult.hasErrors()){
//            String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("添加失败");
//            result.setError(error);
//            return result;
//        }
//        boolean avaiable = checkAccoutAvaiable(user.getUsername());
//
//        if(avaiable){
//            //添加用户
//             OperationUser insertUser = mapper.map(user,OperationUser.class);
//            //密码加密
//            insertUser.setPassword(EncryptUtils.md5crypt(insertUser.getPassword()));
//             OperationUser addUser = operationUserRepository.save(insertUser);
////            插入该用户能操作的菜单
//            user.getMenuIds().stream().forEach(menuId->{
//                UserMenu userMenu = new UserMenu(addUser.getId(),menuId);
//                userMenuRepository.save(userMenu);
//            });
//            //插入该用户所具备的权限
//
//            user.getPermissionIds().stream().forEach(permissionId->{
//                UserPermission permission = new UserPermission(addUser.getId(),permissionId);
//                userPermissionRepository.save(permission);
//            });
//
//            return result;
//
//        }
//        result.setError("用户名已存在");
//        return result;
//        return null;
//    }

    @RequestMapping("user_list")
    public Result getUserList(Result result) {
        List<OperationUser> opUsers = operationUserRepository.findAllNotAdminOperationUser();
        List<DisplayUser> userList = dozerHelper.mapList(opUsers, DisplayUser.class);
        result.addData("userlist", userList);
        return result;

    }

    @RequestMapping("prepare_update_user")

    public Result prepareForUpdate(String username, Result result) {
        Integer userId = operationUserRepository.findByUsername(username).getId();
        List<Integer> opIds = operationRepository.findOperationIdsByUserId(userId);
        List<Menu> menus = menuRepository.findAll();
        menus.stream().forEach(menu -> {
            menu.getOperations().stream()
                    .filter(operation -> opIds.contains(operation.getId()))
                    .forEach(operation -> {
                        operation.setSelected(true);
                    });
        });
        Menu2Selector selector = new Menu2Selector();
        selector.select(menus);

        result.addData("menus", selector.getSelectResult());
        return result;
    }

    @RequestMapping("update_user")

    public Result updateUser (@RequestBody UpdateUserForm userForm) {
        return userService.updateUserMenuList(userForm.getUsername(),userForm.getMenuIds());
    }









    private boolean checkAccoutAvaiable(String username) {
        return username == null ? false : operationUserRepository.findByUsername(username) == null;
    }


}
