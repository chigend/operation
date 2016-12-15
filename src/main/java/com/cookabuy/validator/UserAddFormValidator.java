package com.cookabuy.validator;

import com.cookabuy.entity.operation.dto.AddUserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 该类用来做添加用户的表单验证
 *
 * @author yejinbiao
 * @create 2016-12-14-下午5:58
 */
@Slf4j
public class UserAddFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AddUserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddUserForm form = (AddUserForm)target;
        if(StringUtils.isEmpty(form.getUsername())){
            log.info("validate username fail");
            errors.reject("账户名不能为空");

        }else if(StringUtils.isEmpty(form.getPassword())){
            log.info("validate password fail");
            errors.reject("密码不能为空");
        }
    }
}
