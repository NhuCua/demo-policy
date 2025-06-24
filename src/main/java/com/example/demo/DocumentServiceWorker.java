package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DocumentServiceWorker {

    private final JdbcTemplate serviceJdbc;

    public DocumentServiceWorker(@Qualifier("serviceJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.serviceJdbc = jdbcTemplate;
    }

    public void printAllDocuments() {
        List<Map<String, Object>> docs = serviceJdbc.queryForList("SELECT * FROM documents");
        docs.forEach(doc -> System.out.println("🛠️ [Job] " + doc));
    }
}
