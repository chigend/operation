package com.cookabuy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2016/12/12
 */
@Slf4j
@Controller
public class TestController {
    @RequestMapping("load")
    public String load(){
        return "public/menu::menu";
    }
}
