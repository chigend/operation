package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.AddUserForm;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.entity.operation.po.UserMenu;
import com.cookabuy.entity.operation.po.UserPermission;
import com.cookabuy.repository.operation.OperationUserRepository;
import com.cookabuy.repository.operation.UserMenuRepository;
import com.cookabuy.repository.operation.UserPermissionRepository;
import com.cookabuy.util.EncryptUtils;
import com.cookabuy.util.Result;
import com.cookabuy.validator.UserAddFormValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 2016/12/13
 */
@Controller
@RequestMapping("operate")
public class OperationUserController {
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private OperationUserRepository operationUserRepository;

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;


    @InitBinder("addUserForm")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserAddFormValidator());
    }
    @RequestMapping("/add_user")
    public @ResponseBody Result addUser(@RequestBody@Valid AddUserForm user, BindingResult bindingResult, Result result){
        if(bindingResult.hasErrors()){
            String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("添加失败");
            result.setError(error);
            return result;
        }
        boolean avaiable = checkAccoutAvaiable(user.getUsername());

        if(avaiable){
            //添加用户
             OperationUser insertUser = mapper.map(user,OperationUser.class);
            //密码加密
            insertUser.setPassword(EncryptUtils.md5crypt(insertUser.getPassword()));
             OperationUser addUser = operationUserRepository.save(insertUser);
//            插入该用户能操作的菜单
            user.getMenuIds().stream().forEach(menuId->{
                UserMenu userMenu = new UserMenu(addUser.getId(),menuId);
                userMenuRepository.save(userMenu);
            });
            //插入该用户所具备的权限

            user.getPermissionIds().stream().forEach(permissionId->{
                UserPermission permission = new UserPermission(addUser.getId(),permissionId);
                userPermissionRepository.save(permission);
            });

            return result;

        }
        result.setError("用户名已存在");
        return result;
    }

    private boolean checkAccoutAvaiable(String username){
        return username == null ? false : operationUserRepository.findByUsername(username) == null;
    }
}
