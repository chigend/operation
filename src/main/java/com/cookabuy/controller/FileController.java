package com.cookabuy.controller;

import com.cookabuy.util.Result;
import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yejinbiao
 * @create 2016-12-23-16:15
 */
@RestController
@RequestMapping("/operate")
public class FileController {
    @RequestMapping("/upload_ad_image")
    public Result uploadAdImage(MultipartFile file,Result result){
        System.out.println(file.getName());
        try {

            byte[]data = file.getBytes();
        }catch (IOException e)  {
            result.setError("文件上传失败");
        }
        return result;
    }
}
