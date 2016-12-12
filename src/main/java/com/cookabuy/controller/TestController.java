package com.cookabuy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2016/12/12
 */
@RestController
public class TestController {
    @ResponseBody
    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
