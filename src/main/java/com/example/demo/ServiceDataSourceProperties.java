package com.example.demo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "service.datasource")
public class ServiceDataSourceProperties {

    private String url;

    private String username;

    private String password;
}