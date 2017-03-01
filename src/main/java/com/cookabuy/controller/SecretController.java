package com.cookabuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yejinbiao
 * @create 2017-03-01-上午9:38
 */
@Controller
@RequestMapping("operate")
public class SecretController {
    @RequestMapping("channel")
    public String getSecret() {
        return "secret";
    }
}
