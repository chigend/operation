package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 该类表示最高级别管理员修改运营人员信息时所提交的表单，包含普通信息，和菜单权限信息
 *@see
 * @author yejinbiao
 * @create 2016-12-13-下午2:15
 */
@Setter
@Getter
public class UpdateUserForm {
    private String username;

    private UpdateInfoForm info;

    private List<Integer> menuIds;

    private List<Integer> operationIds;


}
