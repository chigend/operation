package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.AddUserForm;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.OperationUserRepository;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/add_user")
    public Result addUser(@RequestBody AddUserForm user){
        boolean avaiable = checkAccoutAvaiable(user.getUsername());
        if(avaiable){
            //添加用户
            OperationUser addUser = mapper.map(user,OperationUser.class);
            operationUserRepository.save(addUser);
            //插入该用户能操作的菜单

            //插入该用户所具备的权限

        }
        return null;
    }

    private boolean checkAccoutAvaiable(String username){
        return username == null ? false : operationUserRepository.findByUsername(username) == null;
    }
}
