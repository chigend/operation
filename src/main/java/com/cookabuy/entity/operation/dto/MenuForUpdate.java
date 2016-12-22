package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 该类用于最高级别的管理员在修改其他运营人员的菜单权限分配时所提交的表单，同时包含是否选中属性，
 */
@Setter
@Getter
public class MenuForUpdate {
    private String name;
    private Integer id;
    private Integer permissionId;

    private boolean selected;
}
