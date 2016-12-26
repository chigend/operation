package com.cookabuy.service;

import com.cookabuy.entity.operation.dto.UpdateUserForm;
import com.cookabuy.entity.operation.po.*;
import com.cookabuy.repository.operation.*;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**处理用户相关的业务类
 * @author yejinbiao
 * @create 2016-12-22-17:06
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private OperationUserRepository operationUserRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private UserOpRepository userOpRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;




//    @Autowired
//    private

//    /**
//     *根据菜单id的集合 重新分配给用户新的菜单list，即删除原来的菜单list  重新添加
//     * @return 返回处理结果
//     */
//    @Transactional(value = "operationTransactionManager",rollbackFor = Exception.class)
//    public Result updateUserMenuList (String username,List<Integer> menuIds) {
////        OperationUser user = operationUserRepository.findByUsername(username);
////        if(user == null){
////            return new Result("用户名不存在");
////        }
////        //删除该用户下所有的菜单项
////        Integer userId = user.getId();
////        menuRepository.deleteByUserId(userId);
////        log.info("delete original menu successfully");
////        //新增用户菜单
////
////        menuIds.stream().distinct().forEach(menuId ->{
////            UserMenu userMenu = new UserMenu(userId,menuId);
////            userMenuRepository.save(userMenu);
////        });
//        return new Result();
//    }

    @Transactional(value = "operationTransactionManager",rollbackFor = Exception.class)
    public Result updateUser(UpdateUserForm form){
        OperationUser user = operationUserRepository.findByUsername(form.getUsername());
        if(user == null){
            return new Result("用户名不存在");
        }
        //删除该用户下所有的菜单项
        Integer userId = user.getId();
        menuRepository.deleteByUserId(userId);
        log.info("delete original menu successfully");
        //新增用户菜单

        form.getMenuIds().stream().distinct().forEach(menuId ->{
            UserMenu userMenu = new UserMenu(userId,menuId);
            userMenuRepository.save(userMenu);
        });
        //删除该用户下所有的操作Operation项
        userOpRepository.deleteByUserId(userId);
        //为该用户生成新的Operation项，
        form.getOperationIds().stream().distinct().forEach(opId->{
            UserOp userOp = new UserOp(userId,opId);
            userOpRepository.save(userOp);
        });
        //删除该用户下所有的权限
        userPermissionRepository.deleteByUserId(userId);
        //也为该用户分配所对应operation的权限
        form.getPermissionIds().stream().distinct().forEach(permissionId->{
            UserPermission userPermission = new UserPermission(userId,permissionId);
            userPermissionRepository.save(userPermission);
        });
        return new Result();
    }
}
