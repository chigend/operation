package com.cookabuy.spring.validator;

import com.cookabuy.entity.service.dto.ReplaceRecommendForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 2016/12/8
 */
@Slf4j
@Component
public class ReplaceRecommendFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ReplaceRecommendForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("validate ReplaceRecommendForm with validator:{}",this.getClass().getName());
        ReplaceRecommendForm form = (ReplaceRecommendForm)target;
        if(StringUtils.isEmpty(form.getPageName())){
            log.info("pagename invalid");
            errors.reject("pagename can not be null");
        }else if(StringUtils.isEmpty(form.getLocation())){
            log.info("location invalid");
            errors.reject("location can not be null");
        }else if(form.getPosition() == null){
            log.info("position invalid");
            errors.reject("position can not be null");
        }else if(form.getItemId() == null){
            log.info("itemId invalid");
            errors.reject("itemId can not be null");
        }
    }
}
