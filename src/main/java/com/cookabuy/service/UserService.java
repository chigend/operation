package com.cookabuy.service;

import com.cookabuy.entity.operation.po.Operation;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.entity.operation.po.UserMenu;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.repository.operation.OperationRepository;
import com.cookabuy.repository.operation.OperationUserRepository;
import com.cookabuy.repository.operation.UserMenuRepository;
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

    /**
     *根据菜单id的集合 重新分配给用户新的菜单list，即删除原来的菜单list  重新添加
     * @return 返回处理结果
     */
    @Transactional(value = "operationTransactionManager",rollbackFor = Exception.class)
    public Result updateUserMenuList (String username,List<Integer> menuIds) {
        OperationUser user = operationUserRepository.findByUsername(username);
        if(user == null){
            return new Result("用户名不存在");
        }
        //删除该用户下所有的菜单项
        Integer userId = user.getId();
        menuRepository.deleteByUserId(userId);
        log.info("delete original menu successfully");
        //新增用户菜单

        menuIds.stream().forEach(menuId ->{
            UserMenu userMenu = new UserMenu(userId,menuId);
            userMenuRepository.save(userMenu);
        });
        return new Result();
    }

//    @Transactional(value = "operationTransactionManager",rollbackFor = Exception.class)
//    public
}
