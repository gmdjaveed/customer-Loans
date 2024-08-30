# Build Customer and Loan Microservices with Spring Boot, Docker, Kubernetes step by step
## Build different versions in increasing complexitity and features!

Learn how to create enterprise and production ready Microservices with Spring, Spring Cloud, Docker and Kubernetes.

## Features covered
* Feature 1- Building microservices using Spring Boot
* Feature 2 - Handle deployment, portability &  scalability of microservices using Docker
* Feature 3 - Using MySQL DBs inside microservices
* Feature 4 - Service Discovery & Service Registration in microservices
* Feature 5 - Gateway, Routing & Cross cutting concerns in Microservices
* Feature 6 - Making Microservices Resilient
* Feature 7 - Observability and monitoring of microservices
* Feature 8 - Microservices Security
* Feature 9 - Event Driven microservices using RabbitMQ,Spring Cloud Functions & Stream
* Feature 10 - Event Driven microservices using Kafka,Spring Cloud Functions & Stream
* Feature 11 - Container Orchestration using Kubernetes
* Feature 12 - Deep dive on Helm
* Feature 13 - Server-side service discovery and load balancing using Kubernetes
* Feature 14 - Deploying microservices into cloud K8s cluster
* Feature 15 - Introduction to K8s Ingress, Service Mesh (Istio) & mTLS


## Swagger endpoints
### Customer Microservice Swagger endpoint:
```
http://localhost:8080/swagger-ui/index.html
```

### Loans Microservice Swagger endpoint:
```
http://localhost:8090/swagger-ui/index.html
```

### Details:

* Feature 1- Building microservices using Spring Boot
```
  - Build Customer and Loans microservices and run using Intellij and test through postman.
```  
* Feature 2 - Handle deployment, portability &  scalability of microservices using Docker
```
  - Different ways to dockerize the microservice applications.
  - Customer : Use Dockerfile to build the docker image
  - Loans : Use Maven plugin 'Buildpacks' to build the docker image. This does not required 'Dockerfile'
  - Note: In later Feature versions we will use only 'Google Jib' (applicable only for Java) maven plugin to build docker images.
  - Workspace:
  - To build jar for each of the microservices from their root directory (open terminal from intellij), run below command
  -- mvn clean install 
```

### Credit:
```
The credit for building these microservices applications with various features goes to Udemy tutor Madan Reddy for his 
course '[NEW] Master Microservices with SpringBoot,Docker,Kubernetes' Develop Microservices with Java, Spring Boot, 
Spring Cloud, Docker, Kubernetes, Helm, Microservices Security'

Course URL : https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/
```