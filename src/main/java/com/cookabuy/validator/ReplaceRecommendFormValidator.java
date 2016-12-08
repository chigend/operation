package com.cookabuy.validator;

import com.cookabuy.entity.dto.input.ReplaceRecommendForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 2016/12/8
 */
@Slf4j
public class ReplaceRecommendFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ReplaceRecommendForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
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
