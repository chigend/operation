package config;
import com.alibaba.druid.pool.DruidDataSource;
import com.cookabuy.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;
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
}
