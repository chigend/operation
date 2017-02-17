package config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cookabuy.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yejinbiao
 * @create 2017-02-17-下午3:55
 */
@Configuration
public class TestServiceDataSourceConfiguration {
    @Bean
    public DataSource serviceDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        PropertiesUtil.loadProperties("application.properties");
        dataSource.setDriverClassName(PropertiesUtil.getValue("spring.datasource.driver-class-name"));
        dataSource.setUrl(PropertiesUtil.getValue("spring.datasource.url"));
        dataSource.setUsername(PropertiesUtil.getValue("spring.datasource.username"));
        dataSource.setPassword(PropertiesUtil.getValue("spring.datasource.password"));
        return dataSource;
    }
}
