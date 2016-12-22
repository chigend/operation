package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 最高级管理员对其他普通运营人员的姓名和密码进行修改所提交的表单
 * 与此表单同时被提交的还有另外一个表单
 * @see MenuForUpdate
 * @author yejinbiao
 * @create 2016-12-22-9:41
 */

@Setter
@Getter
public class AdminResetNamePasswordForm {
    private String realName;

    private String password;

    private String confirmPassword;
}
