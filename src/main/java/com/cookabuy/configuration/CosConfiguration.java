package com.cookabuy.configuration;

import com.cookabuy.properties.CosProperties;
import com.cookabuy.thirdParty.cos.FileHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * @author yejinbiao
 * @create 2016-12-23-15:29
 */
@Configuration

@EnableConfigurationProperties(CosProperties.class)
public class CosConfiguration {

    @Bean
    public FileHelper fileHelper(CosProperties properties){
       return new FileHelper(properties);
    }


}
