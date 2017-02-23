package com.cookabuy.controller;

import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.service.GetService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

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
    public Result uploadStoreImg(@RequestParam UUID storeId, MultipartFile image) {
        System.out.println(storeId);
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
    public Result uploadItemImg(@RequestParam UUID itemId, MultipartFile image) {
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


}
