package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 该类表示添加运营用户时提交的表单
 *
 * @author yejinbiao
 * @create 2016-12-13-下午2:15
 */
@Setter
@Getter
public class AddUserForm {
    private String username;

    private String password;

    private String realName;

    private List<Integer> menuIds;

    private List<Integer> permissionIds;


}
