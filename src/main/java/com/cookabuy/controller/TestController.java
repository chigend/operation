package com.cookabuy.controller;

import com.alibaba.fastjson.JSON;
import com.cookabuy.constant.CosConstant;
import com.cookabuy.entity.tmp.Test;
import com.cookabuy.thirdParty.cos.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
  public void testImage(@RequestParam("storeIds")List<Integer> storeIds ,@RequestParam("image") List<MultipartFile> files){
        System.out.println(files.size());
        files.stream().map(MultipartFile::getOriginalFilename).forEach(System.out::println);
        storeIds.stream().forEach(System.out::println);
}



}
