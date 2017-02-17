package config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cookabuy.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yejinbiao
 * @create 2017-02-17-下午3:56
 */

@Configuration
public class TestOperationDataSourceConfiguration {
    @Bean
    public DataSource operationDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        PropertiesUtil.loadProperties("application.properties");
        dataSource.setDriverClassName(PropertiesUtil.getValue("operation.datasource.driver-class-name"));
        dataSource.setUrl(PropertiesUtil.getValue("operation.datasource.url"));
        dataSource.setUsername(PropertiesUtil.getValue("operation.datasource.username"));
        dataSource.setPassword(PropertiesUtil.getValue("operation.datasource.password"));
        return dataSource;
    }
}
