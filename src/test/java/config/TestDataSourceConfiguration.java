package config;
import com.alibaba.druid.pool.DruidDataSource;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.PropertiesUtil;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class TestDataSourceConfiguration {
    @Bean
    @Primary
    public DataSource serviceDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        PropertiesUtil.loadProperties("application.properties");
        dataSource.setDriverClassName(PropertiesUtil.getValue("spring.datasource.driver-class-name"));
        dataSource.setUrl(PropertiesUtil.getValue("spring.datasource.url"));
        dataSource.setUsername(PropertiesUtil.getValue("spring.datasource.username"));
        dataSource.setPassword(PropertiesUtil.getValue("spring.datasource.password"));
        return dataSource;
    }
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
    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        return new DozerBeanMapper();
    }

    @Bean
    public DozerHelper dozerHelper(){
        return new DozerHelper();
    }
}
