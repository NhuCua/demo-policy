package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableConfigurationProperties(ServiceDataSourceProperties.class)
public class DataSourceConfig {

    @Bean(name = "serviceDataSource")
    public DataSource serviceDataSource(ServiceDataSourceProperties props) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(props.getUrl());
        ds.setUsername(props.getUsername());
        ds.setPassword(props.getPassword());
        return ds;
    }

    @Bean(name = "serviceJdbcTemplate")
    public JdbcTemplate serviceJdbcTemplate(@Qualifier("serviceDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
