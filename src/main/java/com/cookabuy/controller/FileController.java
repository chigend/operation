package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.AddAdForm;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.service.GetService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.cookabuy.constant.CosConstant.*;
import static com.cookabuy.constant.ErrorConstant.UPLOAD_IMAGE_FAIL;

/**
 * @author yejinbiao
 * @create 2016-12-23-16:15
 */
@RestController
@RequestMapping("/operate")
public class FileController {
    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private RecommendStoreRepository recommendStoreRepository;

    @Autowired
    private GetService getService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @RequestMapping("/upload_store_img")
    public Result uploadStoreImg(Long storeId, MultipartFile image) {
        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_STORE_PATH, image);
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        Optional<String> originCosUrl = Optional.ofNullable(getService.getStorePicUrl(storeId));
        Result result = updateService.updateStoreUrl(storeId, url);
        //如果elasticsearch上给store添加（或者更新）了图片并索引成功，如果原来该store的pic_url存在，则删除原来的cos上的该图片
        result.ifSuccess(() -> originCosUrl.ifPresent(value -> fileHelper.deleteFile(BUCKET, value)));

        return result;
    }

    @RequestMapping("/upload_item_img")
    public Result uploadItemImg(Long itemId, MultipartFile image) {
        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_ITEM_PATH, image);
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        Optional<String> originalCosUrl = Optional.ofNullable(getService.getItemPicUrl(itemId));
        Result result =  updateService.updateItemUrl(itemId, url);
        //如果elasticsearch上给store添加（或者更新）了图片并索引成功，如果原来该store的pic_url存在，则删除原来的cos上的该图片
        result.ifSuccess(() ->  originalCosUrl.ifPresent(value -> fileHelper.deleteFile(BUCKET, value)));
        return result;
    }

    //用于爆款专区的广告图上传
    @RequestMapping("/upload_ad_img")
    public Result uploadAdImg(AddAdForm addAdForm) {
        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, addAdForm.getImage());
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        //todo 删除之前的
        Ad ad = mapper.map(addAdForm, Ad.class);
        ad.setPicUrl(url);
        adRepository.save(ad);
        return new Result("picUrl", url);
    }
}
