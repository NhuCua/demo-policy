package com.example.demo.configs;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class AuthenticatedDataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties authenticatedDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "authenticatedDataSource")
    @Primary
    public DataSource authenticatedDataSource() {
        return authenticatedDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
