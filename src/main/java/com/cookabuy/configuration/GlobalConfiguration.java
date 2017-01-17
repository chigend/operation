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

    /**
     *
     *运营后台菜单的目录顺序在此指定
     */
    @Bean(name = "menuCategoryOrder")
    public Map<String, Integer> menuCategoryOrder() {
        HashMap<String, Integer> order = new HashMap<>();
        order.put("频道管理", 2);
        order.put("公共板块管理", 3);
        order.put("首页管理", 1);
        order.put("账户管理", 4);
        return order;
    }
}
