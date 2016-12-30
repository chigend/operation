package com.cookabuy.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * 复合validator，内置一个包含所有{@Validator}的集合，由于在spring中一个controller里只能绑定一个validator，所以可通过此类的方式
 * 来完成使用多个validator的目的,另通过ValidatorPostProcessor在spring容器启动过程中将所有实现了Validator的类加入此类中
 *
 * @author yejinbiao
 * @create 2016-12-20-15:44
 */
@Component
public class CompoundValidator implements Validator{
    private Set<Validator> validators = new HashSet<>();

    @Override
    public boolean supports(Class<?> clazz) {
        return validators.stream().anyMatch(validator -> validator.supports(clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        validators
                .stream()
                .filter(validator -> validator.supports(target.getClass()))
                .forEach(validator -> validator.validate(target,errors));
    }

    public void addValidator(Validator validator){
        this.validators.add(validator);
    }
}
