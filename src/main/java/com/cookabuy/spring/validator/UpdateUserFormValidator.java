package com.cookabuy.spring.validator;


import com.cookabuy.entity.operation.dto.UpdateUserForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 对最高级权限管理员对其他运营人员信息进行修改时所提交的表单进行验证
 * @see com.cookabuy.entity.operation.dto.UpdateUserForm
 * @author yejinbiao
 * @create 2016-12-27-10:22
 */
@Component
public class UpdateUserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UpdateUserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateUserForm form = (UpdateUserForm)target;
        if(StringUtils.isEmpty(form.getRealName())){
            errors.reject("真实名字不能为空");
        }


    }
}
