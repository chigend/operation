package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 该类用于添加运营用户，表示创建的用户所具有操作权限的菜单
 */
@Setter
@Getter
public class MenuForAddUser {
    private String name;
    private Integer id;
    private Integer permissionId;
}
