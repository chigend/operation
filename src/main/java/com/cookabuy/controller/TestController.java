package com.cookabuy.controller;

import com.cookabuy.entity.tmp.Student;
import com.cookabuy.spring.aop.annotation.RequiresPermission;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 2016/12/12
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private FileHelper fileHelper;

    @RequestMapping("test")
    public void test() {
        System.out.print("test");
    }


    @RequestMapping("/testimage")
    public void testImage(@RequestParam("storeIds") List<Integer> storeIds, @RequestParam("image") List<MultipartFile> files) {
        System.out.println(files.size());
        files.stream().map(MultipartFile::getOriginalFilename).forEach(System.out::println);
        storeIds.stream().forEach(System.out::println);
    }



    @RequestMapping("/test_aop1")
    public Result testaop1(@RequestBody Student s1){
        Result result = new Result();
        result.addData("aop", "test aop1");
        return result;
    }
    @RequestMapping("/test_aop2")
    @RequiresPermission
    public Result testaop2(@RequestBody Student s2){
        Result result = new Result();
        result.addData("aop", "test aop1");
        return result;
    }


}
