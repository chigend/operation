package com.cookabuy.controller;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.service.GetService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.cookabuy.constant.CosConstant.*;
import static com.cookabuy.constant.ElasticSearchConstant.*;

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


    @RequestMapping("/upload_store_img")
    public Result uploadStoreImg(Long storeId, MultipartFile image){
       String url = fileHelper.uploadFile(CosConstant.BUCKET,CosConstant.DIRECOTRY_PREFIX_STORE_PATH,image);
       if (url == null){
           return new Result("图片上传失败");
       }
        Optional<String> originCosUrl = Optional.ofNullable(getService.getStorePicUrl(storeId));
       Result result =  updateService.updateStoreUrl(storeId, url);
       //如果elasticsearch上给store添加（或者更新）了图片并索引成功，如果原来该store的pic_url存在，则删除原来的cos上的该图片
       if (result.getResult().equals(Result.ResponseType.SUCCESS.name())) {
           originCosUrl.ifPresent(value -> {
               fileHelper.deleteFile(BUCKET, value);
           });
       }
        return result;
    }
}
