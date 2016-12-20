package com.cookabuy.validator;

import com.cookabuy.entity.operation.dto.ResetPasswordForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 修改密码的表单验证
 *
 * @author yejinbiao
 * @create 2016-12-20-18:03
 */
@Component
@Slf4j
public class ResetPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ResetPasswordForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("validate ResetPasswordForm with validator:{}",this.getClass().getName());
        ResetPasswordForm form = (ResetPasswordForm) target;
        if(StringUtils.isEmpty(form.getOriginal())){
            log.info("validate field original for form ResetPasswordForm failed");
            errors.reject("原密码不能为空");
        }else if(StringUtils.isEmpty(form.getPassword())){
            log.info("validate field password for form ResetPasswordForm failed");
            errors.reject("密码不能为空");
        }else if(StringUtils.isEmpty(form.getConfirmPassword())){
            log.info("validate field confirmPassword for form ResetPasswordForm failed");
            errors.reject("确认密码不能为空");
        }else if (!form.getPassword().equals(form.getConfirmPassword())){
            log.info("validate field confirmPassword for form ResetPasswordForm failed");
            errors.reject("两次密码不一致");
        }
    }
}
