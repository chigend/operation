package com.cookabuy.controller;

import com.cookabuy.thirdParty.cos.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public void testImage(){
  String localPath = "C:\\Users\\Administrator\\Desktop\\1.jpg";
  fileHelper.uploadFile("test","/ad/4.jpg",localPath);
}



}
