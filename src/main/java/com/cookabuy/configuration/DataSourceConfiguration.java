package com.cookabuy.configuration;

import com.alibaba.druid.pool.DruidDataSource;
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
    //运营相关的datasource
    private static final String operation_driverClass = "operation.datasource.driver-class-name";
    private static final String operation_jdbcUrl = "operation.datasource.url";
    private static final String operation_username = "operation.datasource.username";

    //业务相关的datasource
    private static final String service_driverClass = "service.datasource.driver-class-name";
    private static final String service_jdbcUrl = "service.datasource.url";
    private static final String service_username = "service.datasource.username";
    @Resource
    private Environment env;
    @Bean
    public DruidDataSource operationDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(operation_driverClass));
        dataSource.setUrl(env.getRequiredProperty(operation_jdbcUrl));
        dataSource.setUsername(env.getRequiredProperty(operation_username));
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    @Bean
    @Primary
    public DataSource serviceDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(service_driverClass));
        dataSource.setUrl(env.getRequiredProperty(service_jdbcUrl));
        dataSource.setUsername(env.getRequiredProperty(service_username));
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    
}
