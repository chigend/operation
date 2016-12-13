package com.cookabuy.validator;

import com.cookabuy.entity.operation.dto.LoginForm;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 2016/12/12
 */
public class LoginFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(LoginForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm form = (LoginForm)target;
        if(StringUtils.isEmpty(form.getUsername())){
            errors.reject("用户名不能为空");
        }else if(StringUtils.isEmpty(form.getPassword())){
            errors.reject("密码不能为空");
        }
    }
}
