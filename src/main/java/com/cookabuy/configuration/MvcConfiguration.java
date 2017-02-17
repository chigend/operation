package com.cookabuy.configuration;

import com.cookabuy.spring.converter.StringToUUIDConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yejinbiao
 * @create 2017-02-16-下午4:23
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUUIDConverter());
        super.addFormatters(registry);
    }
}
