package com.cookabuy.configuration;

import com.cookabuy.thirdParty.dozer.beanfactory.UUIDBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 2016/12/8
 */
@Configuration
@Slf4j
public class DozerConfiguration {
    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        BeanMappingBuilder uuidBuilder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(UUID.class, UUID.class, TypeMappingOptions.oneWay(), TypeMappingOptions.beanFactory(UUIDBeanFactory.class.getName()));
            }
        };
        dozerBeanMapper.addMapping(uuidBuilder);
        return dozerBeanMapper;
    }
}
