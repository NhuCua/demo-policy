package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JwtAwareDataSource extends DriverManagerDataSource {

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = super.getConnection();
        try {
            injectJwt(conn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection conn = super.getConnection(username, password);
        try {
            injectJwt(conn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private void injectJwt(Connection conn) throws SQLException, JsonProcessingException {
        String jwt = JwtContextHolder.getJwt();

        Map<String, String> claims = new HashMap<>();
        claims.put("user_id", "abc");
        claims.put("org_id", "xyz");
        claims.put("role", "admin");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(claims);

//        if (jwt != null) {
                conn.createStatement().execute("SET jwt.claims.context = '" + json + "'");

//        }
    }
}
