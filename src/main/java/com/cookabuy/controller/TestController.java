package com.cookabuy.controller;

import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/test_result")
    public Result testResult(Result result) {
        return result;
    }

    @RequestMapping("/test_aop1")
    public Result testaop1(Result result){
        result.addData("aop", "test aop1");
        return result;
    }
    @RequestMapping("/test_aop2")
    @MenuItem
    public Result testaop2(Result result){
        result.addData("aop", "test aop2");
        return result;
    }

    @RequestMapping("permission1")
    @RequiresPermissions("modify")
    public Result testPermission1(Result result) {
        return result;
    }
    @RequestMapping("permission2")
    @RequiresPermissions("add")
    public Result testPermission2(Result result) {
        return result;
    }

}
