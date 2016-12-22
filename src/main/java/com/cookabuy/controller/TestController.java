package com.cookabuy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2016/12/12
 */
@Slf4j
@RestController
public class TestController {
  @RequestMapping("test")
    public void test(){
      System.out.print("test");
  }






}
