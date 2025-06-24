package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JwtSessionConfigurer {

    private final JdbcTemplate jdbcTemplate;

    public JwtSessionConfigurer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJwtSession(String userId, String orgId) {
        jdbcTemplate.queryForObject("SELECT set_config('jwt.claims.user_id', ?, false)", String.class, userId);
        jdbcTemplate.queryForObject("SELECT set_config('jwt.claims.organization_id', ?, false)", String.class, orgId);
        System.out.println("JWT session config success!");
    }
}
