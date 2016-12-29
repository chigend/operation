package com.cookabuy.controller;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.entity.service.dto.RecommendStoreDTO;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.entity.service.po.Store;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.repository.service.StoreRepository;
import com.cookabuy.service.GetService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.cookabuy.constant.CosConstant.*;
import static com.cookabuy.constant.PageContant.*;
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
    @RequestMapping("/recommend_store")
    public Result recommendStore(@RequestBody List<RecommendStoreDTO> stores) {
        List<RecommendStore> recommendStores = dozerHelper.mapList(stores, RecommendStore.class);
        int maxPosition = recommendStoreRepository.findMaxPositionByPage(INDEX);
        for (RecommendStore store : recommendStores){
            //如果推荐店铺列表中已经有该店铺则跳过
            if(recommendStoreRepository.exists(store.getStoreId())){
                continue;
            }
            store.setInsertedAt(new Date());
            store.setUpdatedAt(new Date());
            store.setPage(INDEX);
            store.setPosition(++maxPosition);
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
    public Result listStores(String page, Result result) {
        List<RecommendStore> recommendStores = recommendStoreRepository.findByPageOrderByPositionAsc(page);
        List<RecommendStoreDTO> dtos = dozerHelper.mapList(recommendStores,RecommendStoreDTO.class);
        dtos.stream().forEach(dto -> {
            Store store = storeRepository.findOne(dto.getStoreId());
            dto.setLocation(store.getLocation());
            dto.setStoreName(store.getStoreName());
        });
        result.addData("stores", dtos);
        return result;
    }

    @RequestMapping("delete_store")
    public Result deleteStore(Integer id, Result result) {
        Optional<RecommendStore> recommendStore = Optional.ofNullable(recommendStoreRepository.findOne(id));
        //如果推荐店铺的url存在表示该图片已经上传cos，那么在删除该店铺之前首先删除在cos上的图片
        recommendStore.map(RecommendStore::getPicUrl).ifPresent(url->{
            fileHelper.deleteFile(CosConstant.DIRECTORY_PREFIX_STORE_PATH,url);
        });
        recommendStore.map(RecommendStore::getId).ifPresent(recommendStoreRepository::delete);
        return result;
    }

    @RequestMapping("update_store_img")
    public Result updateStoreImg(Integer id, MultipartFile image) {
        Optional<RecommendStore> store = Optional.ofNullable(recommendStoreRepository.findOne(id));
        if (!store.isPresent()){
            return new Result("该店铺未在推荐列表");
        }

        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_STORE_PATH, image);
        if (url == null) {
            return new Result("图片修改失败");
        }
        Long storeId = store.get().getStoreId();
        //获取elastic 索引上原来的url
        Optional <String> cosOriginalUrl = Optional.ofNullable(getService.getStorePicUrl(storeId));
        //更新elastic 索引上的url
        Result result = updateService.updateStoreUrl(storeId,url);
        //如果更新成功就把cos上原来的url删除
        if (result.getResult().equals(Result.ResponseType.SUCCESS.name())){
            cosOriginalUrl.ifPresent(value ->{
                fileHelper.deleteFile(BUCKET,value);
            });
        }

        //修改推荐店铺的图片url为上述新上传的图片url
        store.ifPresent(value ->{
            value.setPicUrl(url);
            recommendStoreRepository.save(value);
        });

        result.addData("picUrl",url);
        return result;



    }

    @RequestMapping("toggle_store_effective")
    public Result toggoleStoreEffective (Integer id, Result result) {
        recommendStoreRepository.toggleEffectiveById(id);
        return result;
    }

}