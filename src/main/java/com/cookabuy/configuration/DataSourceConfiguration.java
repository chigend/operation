package com.cookabuy.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 2016/12/13
 */
@Configuration
public class DataSourceConfiguration {
    private static final String driverClass = "operation.datasource.driver-class-name";
    private static final String jdbcUrl = "operation.datasource.url";
    private static final String username = "operation.datasource.username";
    private static final String password = "operation.datasource.password";
    @Resource
    private Environment env;
    @Bean
    public DruidDataSource operationDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(driverClass));
        dataSource.setUrl(env.getRequiredProperty(jdbcUrl));
        dataSource.setUsername(env.getRequiredProperty(username));
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource serviceDataSource() {
        return DataSourceBuilder.create().build();
    }

    
}
