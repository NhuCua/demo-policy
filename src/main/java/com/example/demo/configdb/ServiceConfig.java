package com.example.demo.configdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;


@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = {
                "com.example.demo.repository.service",
        },
        entityManagerFactoryRef = "productEntityManager",
        transactionManagerRef = "productTransactionManager"
)
public class ServiceConfig extends AbstractMultiDataSourceConfig{
    @Autowired
    public ServiceConfig(Environment env) {
        super(env);
    }

    @Bean
    public DataSource productDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(env.getProperty("admin.datasource.url"));
        dataSource.setUsername(env.getProperty("admin.datasource.username"));
        dataSource.setPassword(env.getProperty("admin.datasource.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        return super.entityManager(productDataSource(), new String[]{"com.example.demo.entity"}, "productPU");
    }

    @Bean
    public PlatformTransactionManager productTransactionManager() {
        return super.transactionManager(productEntityManager());
    }

}
