package com.cookabuy.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 2016/12/5
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@Slf4j
public class DataSourceConfiguration {

    @Bean
    public DataSource getDataSource(DataSourceProperties dp) {
        log.info("datasource properties {}",dp);
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(dp.getDriverClassName());
        dataSource.setUrl(dp.getUrl());
        dataSource.setUsername(dp.getUsername());
        dataSource.setPassword(dp.getPassword());
        return dataSource;
    }
}
