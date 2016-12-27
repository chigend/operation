package com.cookabuy.controller;

import com.cookabuy.validator.CompoundValidator;
import com.cookabuy.validator.ReplaceRecommendFormValidator;
import com.cookabuy.entity.service.dto.ReplaceRecommendForm;
import com.cookabuy.repository.service.RecommendRepository;
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
@RequestMapping("operate")
@Slf4j
public class RecommendController {
//    @RequestMapping()
}
