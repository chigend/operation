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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;

//该类用来做为jpa测试的配置，因为在测试环境中没有spring的自动配置，需自己配置
@Slf4j
@EnableJpaRepositories(basePackages = {"com.cookabuy.repository"})
public class JpaTestConfig {
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        // 设置数据源
        factory.setDataSource(dataSource);
        // 指定Jpa持久化实现厂商类,这里以Hibernate为例
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // 实体类扫描
        factory.setPackagesToScan(new String[] { "com.cookabuy.entity" });
        Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        p.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        p.put("hibernate.show_sql", true);
        p.put("hibernate.format_sql", true);
        p.put("hibernate.hbm2ddl.auto", "update");
        p.put("javax.persistence.query.timeout",30000);
        factory.setJpaProperties(p);
        // 设置了Properties之后，需要调用该方法才能完成属性配置
        factory.afterPropertiesSet();
        // 利用factory来创建bean对象
        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emf);
        return manager;
    }

    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        PropertiesUtil.loadProperties("application.properties");
        dataSource.setDriverClassName(PropertiesUtil.getValue("spring.datasource.driver-class-name"));
        dataSource.setUrl(PropertiesUtil.getValue("spring.datasource.url"));
        dataSource.setUsername(PropertiesUtil.getValue("spring.datasource.username"));
        dataSource.setPassword(PropertiesUtil.getValue("spring.datasource.password"));
        return dataSource;
    }
}
