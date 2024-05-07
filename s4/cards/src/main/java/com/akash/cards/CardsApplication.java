package com.akash.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Cards Microservice REST API Documentation",
                description = "EZBank Cards Microservice REST API Documentation",
                version = "V1",
                contact = @Contact(
                        name = "Akash Aher",
                        email = "akash.aher@neutrinotechlabs.com",
                        url = "www.neutrinotechlabs.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.neutrinotechlabs.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EZBank Cards Microservice REST API Documentation",
                url = "www.neutrinotechlabs.com"
        )
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
