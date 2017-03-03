package com.cookabuy.controller;

import com.cookabuy.constant.PublishType;
import com.cookabuy.entity.service.dto.MoveRecommendStoreForm;
import com.cookabuy.entity.service.dto.RecommendStoreDTO;
import com.cookabuy.entity.service.dto.UpdateRecommendStoreForm;
import com.cookabuy.entity.service.po.PublishLog;
import com.cookabuy.entity.service.po.PublishedStore;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.entity.service.po.Store;
import com.cookabuy.repository.service.PublishLogRepository;
import com.cookabuy.repository.service.PublishedStoreRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.repository.service.StoreRepository;
import com.cookabuy.service.GetService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.cookabuy.constant.CosConstant.BUCKET;
import static com.cookabuy.constant.CosConstant.DIRECTORY_PREFIX_STORE_PATH;
import static com.cookabuy.constant.ErrorConstant.STORE_NOT_IN_RECOMMEND;
import static com.cookabuy.constant.ErrorConstant.UPLOAD_IMAGE_FAIL;
import static com.cookabuy.constant.PageContant.INDEX;
/**
 * 2016/12/5
 */
@RestController
@RequestMapping("operate")
@Slf4j
public class RecommendStoreController {
    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @Autowired
    private FileHelper fileHelper;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private GetService getService;
    @Autowired
    private PublishedStoreRepository activeStoreRepository;
    @Autowired
    private PublishLogRepository publishLogRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @RequestMapping("/recommend_store")
    @RequiresPermissions("recommendStore:add")
    public Result recommendStore(@RequestBody List<RecommendStoreDTO> stores) {
        List<RecommendStore> recommendStores = dozerHelper.mapList(stores, RecommendStore.class);
        int maxPosition = recommendStoreRepository.findMaxPositionByPage(INDEX);
        for (RecommendStore store : recommendStores){
            //如果推荐店铺列表中已经有该店铺则跳过
            if(recommendStoreRepository.findByStoreId(store.getStoreId()) != null){
                continue;
            }
            store.setCreateTime(new Date());
            store.setModifyTime(new Date());
            store.setPage(INDEX);
            store.setPosition(++maxPosition);
            updateService.toggleStoreAdded(store.getStoreId());
            recommendStoreRepository.save(store);
        }
        return new Result();
    }

    /**
     *
     * @param page 指定该推荐店铺的页面位置
     * @param result
     * @return
     */
    @RequestMapping("list_stores")
    @MenuItem
    public Result listStores(String page, Result result) {
        List<RecommendStore> recommendStores = recommendStoreRepository.findByPageOrderByPositionAsc(page);
        List<RecommendStoreDTO> dtos = dozerHelper.mapList(recommendStores,RecommendStoreDTO.class);
        dtos.stream().forEach(dto -> {
            Store store = storeRepository.findOne(dto.getStoreId());
            dto.setLocation(store.getLocation());
            dto.setStoreName(store.getStoreName());
        });
        result.addData("stores", dtos);
        boolean activate = recommendStoreRepository.publishActivate(PublishType.STORE);
        result.addData("activate",activate);
        return result;
    }

    @RequestMapping("delete_store")
    @RequiresPermissions("recommendStore:delete")
    public Result deleteStore(UUID id, Result result) {
        Optional<RecommendStore> recommendStore = Optional.ofNullable(recommendStoreRepository.findOne(id));
        //如果推荐店铺的url存在表示该图片已经上传cos，那么在删除该店铺之前首先删除在cos上的图片
        recommendStore.map(RecommendStore::getPicUrl).ifPresent(url->
            fileHelper.deleteFile(DIRECTORY_PREFIX_STORE_PATH, url)
        );
        recommendStore.ifPresent(store -> {
            updateService.toggleStoreAdded(store.getStoreId());
            recommendStoreRepository.delete(store.getId());
        });
        return result;
    }

    @RequestMapping("update_store_img")
    @RequiresPermissions("recommendStore:image:modify")
    public Result updateStoreImg(@RequestParam  UUID storeId, MultipartFile image) {
        Optional<RecommendStore> store = Optional.ofNullable(recommendStoreRepository.findByStoreId(storeId));
        if (!store.isPresent()){
            return new Result(STORE_NOT_IN_RECOMMEND);
        }

        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_STORE_PATH, image);
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        //获取elastic 索引上原来的url
        Optional <String> cosOriginalUrl = Optional.ofNullable(getService.getStorePicUrl(storeId));
        //更新elastic 索引上的url
        Result result = updateService.updateStoreUrl(storeId,url);
        //如果更新成功就把cos上原来的url删除
        result.ifSuccess(() -> cosOriginalUrl.ifPresent(value -> fileHelper.deleteFile(BUCKET, value)));

        //修改推荐店铺的图片url为上述新上传的图片url
        store.ifPresent(value -> {
            value.setPicUrl(url);
            recommendStoreRepository.save(value);
        });

        result.addData("picUrl",url);
        return result;
    }

//    @RequestMapping("toggle_store_effective")
//    @RequiresPermissions("recommendStore:hide")
//    public Result toggoleStoreEffective (Integer id, Result result) {
//        recommendStoreRepository.toggleEffectiveById(id);
//        return result;
//    }

    @RequestMapping("replace_store")
    @RequiresPermissions("recommendStore:modify")
    public Result replaceStore(@RequestBody UpdateRecommendStoreForm form) {

        //todo  delete cos picurl
        RecommendStore store = recommendStoreRepository.findOne(form.getId());
        store.setStoreId(form.getStoreId());
        store.setPicUrl(form.getPicUrl());
        recommendStoreRepository.save(store);
        return new Result();
    }

    @RequestMapping("move_store")
    @RequiresPermissions("recommendStore:move")
    public Result moveStore(@RequestBody MoveRecommendStoreForm form) {
        RecommendStore store = recommendStoreRepository.findOne(form.getId());
        if (store != null) {
            Integer position = store.getPosition();
            RecommendStore another = recommendStoreRepository.findByPageAndPosition(INDEX, form.getPosition());
            if (another == null) {
                return new Result("移动失败");
            }
            store.setPosition(form.getPosition());
            another.setPosition(position);
            recommendStoreRepository.save(Arrays.asList(store, another));
        }
        return new Result();
    }

    @RequiresPermissions("recommendStore:publish")
    @RequestMapping("publish_stores")
    public Result publishStores() {
        activeStoreRepository.deleteAll();
        recommendStoreRepository.findByPage(INDEX).stream()
                .forEach(store -> {
                    PublishedStore as = new PublishedStore(store.getStoreId(), store.getPosition(), store.getPicUrl(), store.getPage());

                    activeStoreRepository.save(as);
                });
        publishLogRepository.save(new PublishLog(PublishType.STORE));
        return new Result();
    }

}
