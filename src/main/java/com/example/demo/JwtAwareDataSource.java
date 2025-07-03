package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class JwtAwareDataSource extends DriverManagerDataSource {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = super.getConnection();
        injectJwt(conn);
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection conn = super.getConnection(username, password);
        injectJwt(conn);
        return conn;
    }

    private void injectJwt(Connection conn)  {
        String jwt = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            jwt = (String) authentication.getCredentials();
        }
        if (jwt == null || jwt.isEmpty()) {
            return;
        }
        try {
            Key key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jwtClaims =   Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            Claims claims = jwtClaims.getBody();

            Map<String, Object> claimsMap = new HashMap<>(claims);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(claimsMap);

            System.out.println("Injecting decoded JWT claims into connection: " + json);

            conn.createStatement().execute("SET jwt.claims.context = '" + json + "'");

        } catch (Exception e) {
            System.out.println("Error parsing JWT: " + e.getMessage());
            throw new RuntimeException("JWT parsing failed.", e);
        }
    }
}
