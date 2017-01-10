package com.cookabuy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置
 * @author yejinbiao
 * @create 2016-12-30-11:24
 */
@Configuration
@EnableScheduling //开启计划任务
public class GlobalConfiguration {
    @Bean(name = "permissionMap")
    public Map<String, String> getRecommendItemPermissionMap() {
       HashMap<String, String> permissionMap = new HashMap<>();
       permissionMap.put("public", "recommendItem:product:add");
       permissionMap.put("index", "recommendItem:hot:add");
       permissionMap.put("hot", "recommendItem:boom:add");
       return permissionMap;
    }
}
