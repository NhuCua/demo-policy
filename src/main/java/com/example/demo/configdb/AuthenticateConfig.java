package com.example.demo.configdb;

import com.example.demo.JwtAwareDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = {
                "com.example.demo.repository.server"
        },
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class AuthenticateConfig extends AbstractMultiDataSourceConfig {
    @Autowired
    public AuthenticateConfig(Environment env) {
        super(env);
    }

    @Primary
    @Bean
    public DataSource userDataSource() {
        JwtAwareDataSource dataSource = new JwtAwareDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        return super.entityManager(userDataSource(), new String[]{"com.example.demo.entity"}  , "userPU");
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {
        return super.transactionManager(userEntityManager());
    }

}
