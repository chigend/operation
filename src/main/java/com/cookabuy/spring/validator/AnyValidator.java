package com.cookabuy.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 由于Validator会对所有的方法参数上的实体进行验证，如果在所有的Validator中没有一个Validator 是support的，则会报Invalid target for validator的异常
 * @author yejinbiao
 * @create 2016-12-20-17:53
 */
@Component
public class AnyValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        //空验证
    }
}
