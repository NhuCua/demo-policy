package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JwtSessionConfigurer {

    private final JdbcTemplate jdbcTemplate;

    public JwtSessionConfigurer(@Qualifier("authenticatedDataSource") DataSource userDs) {
        this.jdbcTemplate = new JdbcTemplate(userDs);
    }

    public void setJwtSession(String userId, String orgId) {
        jdbcTemplate.queryForObject("SELECT set_config('jwt.claims.user_id', ?, false)", String.class, userId);
        jdbcTemplate.queryForObject("SELECT set_config('jwt.claims.organization_id', ?, false)", String.class, orgId);
        System.out.println("JWT session config success!");
    }
}
