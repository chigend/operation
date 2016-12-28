package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.RecommendStoreDTO;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.thirdParty.dozer.DozerHelper;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 2016/12/5
 */
@RestController
@RequestMapping("operate")
@Slf4j
public class RecommendController {
    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @RequestMapping("/recommend_store")
    public Result recommendStore(@RequestBody List<RecommendStoreDTO> stores){
        List<RecommendStore> recommendStores = dozerHelper.mapList(stores, RecommendStore.class);
        recommendStores.stream().forEach(store->{
            store.setInsertedAt(new Date());
            store.setUpdatedAt(new Date());
            recommendStoreRepository.save(store);
        });
        return new Result();
    }
    @RequestMapping("list_stores")
    public Result listStores(Result result){
        result.addData("stores", recommendStoreRepository.findAll());
        return result;
    }

}
