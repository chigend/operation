package com.cookabuy.controller;

import com.cookabuy.constant.PageContant;
import com.cookabuy.entity.service.dto.RecommendStoreDTO;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.entity.service.po.Store;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.repository.service.StoreRepository;
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
    private StoreRepository storeRepository;
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

    /**
     *
     * @param page 指定该推荐店铺的页面位置
     * @param result
     * @return
     */
    @RequestMapping("list_stores")
    public Result listStores(String page, Result result){
        List<RecommendStore> recommendStores = recommendStoreRepository.findByPage(page);
        List<RecommendStoreDTO> dtos = dozerHelper.mapList(recommendStores,RecommendStoreDTO.class);
        dtos.stream().forEach(dto -> {
            Store store = storeRepository.findOne(dto.getStoreId());
            dto.setLocation(store.getLocation());
            dto.setStoreName(store.getStoreName());
        });
        result.addData("stores", dtos);
        return result;
    }

}
