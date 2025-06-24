package com.example.demo.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "admin.datasource")
public class AdminDataSourceProperties {

    private String url;

    private String username;

    private String password;
}