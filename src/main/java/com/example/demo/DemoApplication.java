package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private DataSource userDataSource; // default JPA datasource

    @Autowired
    @Qualifier("serviceDataSource")
    private DataSource adminDataSource;

    @Bean
    CommandLineRunner run(DocumentRepository repository,
                          JwtSessionConfigurer sessionConfigurer,
                          DocumentServiceWorker worker) {
        return args -> {

            System.out.println("\n=======================");
            System.out.println("✅ [INFO] Default DataSource used by JPA (DocumentRepository):");
            System.out.println("↪️  " + userDataSource.getConnection().getMetaData().getUserName() + "\n" );

            System.out.println("✅ [INFO] AdminDataSource used by JdbcTemplate:");
            System.out.println("↪️  " + adminDataSource.getConnection().getMetaData().getUserName());
            System.out.println("=======================\n");

            // 1. Giả lập JWT session cho app
            String jwtUserId = "user_123";
            String jwtOrgId = "org_456";
            sessionConfigurer.setJwtSession(jwtUserId, jwtOrgId);

            // 2. Query bằng repository (RLS, JWT)
            System.out.println("📄 [User Session] Documents:");
            repository.findAll().forEach(doc ->
                    System.out.printf("📄 %d | %s | %s | %s%n", doc.getId(), doc.getTitle(), doc.getContent(), doc.getCreatedAt())
            );

            // 3. Query bằng service role (bypass JWT)
            System.out.println("🛠️ [Service Worker] Documents:");
            worker.printAllDocuments();
        };
    }
}
