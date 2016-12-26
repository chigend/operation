package com.cookabuy.controller;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.thirdParty.cos.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 2016/12/12
 */
@Slf4j
@RestController
public class TestController {
  @Autowired
  private FileHelper fileHelper;
  @RequestMapping("test")
    public void test(){
      System.out.print("test");
  }


@RequestMapping("/testimage")
  public void testImage(MultipartFile file){
  System.out.println(file.getOriginalFilename());
      String url = fileHelper.uploadFile("test", CosConstant.DIRECTORY_PREFIX_AD_PATH,file);
      System.out.println(url);
}



}
