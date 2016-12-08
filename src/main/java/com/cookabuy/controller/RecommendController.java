package com.cookabuy.controller;

import com.cookabuy.validator.ReplaceRecommendFormValidator;
import com.cookabuy.entity.dto.input.ReplaceRecommendForm;
import com.cookabuy.repository.RecommendRepository;
import com.cookabuy.service.RecommendService;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 2016/12/5
 */
@RestController
@RequestMapping("operation")
@Slf4j
public class RecommendController {
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private RecommendService recommendService;
    @InitBinder("replaceRecommendForm")
    public void initBinder(WebDataBinder binder) {
            binder.setValidator(new ReplaceRecommendFormValidator());
    }

    @RequestMapping("/replace_recommend")
    public Result replaceRecommend(Result result, @Valid ReplaceRecommendForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String error = bindingResult.getAllErrors().stream().map(ObjectError::getCode).findFirst().orElse("修改失败");
            result.setError(error);
            return result;

        }
        recommendService.replaceRecommend(form);
        return result;
    }
}
