package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DocumentRepository repository, JwtSessionConfigurer sessionConfigurer) {
        return args -> {
            String jwtUserId = "user_123";
            String jwtOrgId = "org_456";
            String jwtRole = "admin";

            sessionConfigurer.setJwtSession(jwtUserId, jwtOrgId, jwtRole);

            System.out.println("List documents:");
            repository.findAll().forEach(doc ->
                    System.out.printf("📄 %d | %s | %s | %s%n", doc.getId(), doc.getTitle(), doc.getContent(), doc.getCreatedAt())
            );
        };
    }
}
