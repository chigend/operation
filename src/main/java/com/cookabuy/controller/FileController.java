package com.cookabuy.controller;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.service.UpdateService;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

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

    @RequestMapping("/upload_ad_image")
    public Result uploadAdImage(MultipartFile file, Result result){
        System.out.println(file.getName());
        try {

            byte[]data = file.getBytes();
        }catch (IOException e)  {
            result.setError("文件上传失败");
        }
        return result;
    }

    @RequestMapping("/upload_store_img")
    public Result uploadStoreImg(Long storeId, MultipartFile image, Result result){
       String url = fileHelper.uploadFile(CosConstant.BUCKET,CosConstant.DIRECOTRY_PREFIX_STORE_PATH,image);
       if (url == null){
           return new Result("图片上传失败");
       }
       return updateService.updateStoreUrl(storeId,url);
    }
}
