package com.javeed.customer;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.javeed.accounts.controller") })
@EnableJpaRepositories("com.javeed.accounts.repository")
@EntityScan("com.javeed.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Customer microservice REST API Documentation",
                description = "Customer & Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Javeed Ghani",
                        email = "javeed@nodomain.com",
                        url = "https://www.nodomain.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.nodomain.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "Customer & Loans microservice REST API Documentation",
                url = "https://www.nodomain.com/swagger-ui.html"
        )
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
