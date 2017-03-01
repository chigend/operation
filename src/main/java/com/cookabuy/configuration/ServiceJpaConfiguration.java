package com.cookabuy.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//该类为业务类的数据源jpa的配置
@Configuration
@EnableJpaRepositories(transactionManagerRef = "serviceTransactionManager",entityManagerFactoryRef ="serviceEntityManagerFactory"
        ,basePackages = { "com.cookabuy.repository.service" })
@Slf4j
public class ServiceJpaConfiguration {
    @Bean(name = "serviceEntityManagerFactory")
    @Primary
    public EntityManagerFactory getEntityManagerFactoryBean(@Qualifier(value = "serviceDataSource") DataSource dataSource) {
        log.info("configuring service jpa dataSource:{}",dataSource);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        // 设置数据源
        System.out.println(dataSource);
        factory.setDataSource(dataSource);
        // 指定Jpa持久化实现厂商类,这里以Hibernate为例
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // 实体类扫描
        factory.setPackagesToScan(new String[] { "com.cookabuy.entity.service.po" });
        Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        p.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        p.put("hibernate.show_sql", false);
        p.put("hibernate.format_sql", true);
        p.put("hibernate.hbm2ddl.auto", "update");
        p.put("hibernate.ejb.entitymanager_factory_name", "service");
        factory.setJpaProperties(p);
        // 设置了Properties之后，需要调用该方法才能完成属性配置
        factory.afterPropertiesSet();
        // 利用factory来创建bean对象
        return factory.getObject();
    }

    @Bean(name = "serviceTransactionManager")
    @Primary
    public JpaTransactionManager getTransactionManager(@Qualifier("serviceEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emf);
        return manager;
    }


}
