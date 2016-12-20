package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 重置密码的表单提交
 *
 * @author yejinbiao
 * @create 2016-12-20-13:40
 */
@Setter
@Getter
public class ResetPasswordForm {

    private String username;

    private String original;

    private String password;

    private String confirmPassword;
}
