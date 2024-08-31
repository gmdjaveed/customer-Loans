package com.javeed.loans;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
@EnableJpaRepositories("com.eazybytes.loans.repository")
@EntityScan("com.eazybytes.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Customers & Loans microservice REST API Documentation",
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
				description =  "Customers & Loans microservice REST API Documentation",
				url = "https://www.nodomain.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
