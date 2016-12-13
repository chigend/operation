package com.cookabuy.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 2016/12/13
 */
@Configuration
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "operation.datasource")
    public DataSource operationDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource serviceDataSource() {
        return DataSourceBuilder.create().build();
    }


}
