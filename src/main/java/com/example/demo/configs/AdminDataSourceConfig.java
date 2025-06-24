package com.example.demo.configs;

import com.example.demo.properties.AdminDataSourceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableConfigurationProperties(AdminDataSourceProperties.class)
public class AdminDataSourceConfig {

    @Bean(name = "adminDataSource")
    public DataSource adminDataSource(AdminDataSourceProperties props) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(props.getUrl());
        ds.setUsername(props.getUsername());
        ds.setPassword(props.getPassword());
        return ds;
    }

    @Bean(name = "adminJdbcTemplate")
    public JdbcTemplate adminJdbcTemplate(@Qualifier("adminDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
