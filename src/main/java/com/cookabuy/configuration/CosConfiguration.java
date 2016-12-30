package com.cookabuy.configuration;

import com.cookabuy.properties.CosProperties;
import com.cookabuy.thirdParty.cos.FileHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
