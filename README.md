# Build Customers and Loans Microservices with Spring Boot, Docker, Kubernetes step by step
## Build different versions in increasing complexity and features!

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
### Customers Microservice Swagger endpoint:
```
http://localhost:8080/swagger-ui/index.html
```

### Loans Microservice Swagger endpoint:
```
http://localhost:8090/swagger-ui/index.html
```

### Pre-requisite:
```
1) Intellij

2) Intellij Pluggins: Such as 'IntelliJ Lombok plugin', 'YAML', 'Git' etc 

3) Docker Desktop Installed 
    - Optionally install plugins such as 'Logs Explorer'

4) minikube 
   - https://minikube.sigs.k8s.io/docs/start/ - minikube is local Kubernetes, focusing on making it easy to learn and develop for Kubernetes.
   - minikube provisions and manages local Kubernetes clusters optimized for development workflows.
   - 
   - minikube docker installed - Docker image "gcr.io/k8s-minikube/kicbase"
   - https://kubernetes.io/docs/tutorials/kubernetes-basics/create-cluster/cluster-intro/
   - "Minikube is a lightweight Kubernetes implementation that creates a VM on your local machine and deploys a simple cluster containing only one node." or deployed as docker container.

5) cygwin installed to emulate unix commands

6) maven on class
    - Ex: export PATH=/cygdrive/c/Apps/apache-maven-3.9.8/bin:$PATH
```

### Details:
* Postman Import
```
 - Please import 'CustomerLoans.postman_collection.json' into your postman.
```

* Feature 1- Building microservices using Spring Boot
```
  - Build Customers and Loans microservices and run using Intellij and test through postman.
  - Create:
    Ex: http://localhost:8080/api/create
        Method:Post
        Body:
        {
        "name": "Javeed",
        "email": "Javeed@nodomain.com",
        "mobileNumber": "1234567890"
        }
  - Fetch:
    Ex: http://localhost:8080/api/fetch?mobileNumber=1234567890
    
  - Update:
    Ex: http://localhost:8080/api/update
        Method:Put
        Body:
        {
        "name": "Javeed Ghani",
        "email": "Javeed.ghani@nodomain.com",
        "mobileNumber": "1234567890"
        } 

  - Delete:          
    Ex: http://localhost:8080/api/delete?mobileNumber=1234567890             
```  
* Feature 2 - Handle deployment, portability &  scalability of microservices using Docker
```
  - Different ways to dockerize the microservice applications.
  - 1) Dockerfile 2) Buildpacks (Maven plugin) 3) Google Jib (Maven plugin)
  -- Dockerfile: Need to create Dockerfile and add all instructions.
  -- Buildpacks: Will take care of optimization, security, compression of docker file etc
  --             supports multiple langauges such as Java, NodeJs, Go, GraalVM, Python, NGNix, Php etc
  -- Google Jib: Will take care of best practices, caches and smaller sizes. Applicable only for Java.
  -
  - (a) Customers : Use Dockerfile to build the docker image
  - (b) Loans : Use Maven plugin 'Buildpacks' to build the docker image. 
  - Note: In later Feature versions we will use only 'Google Jib'.
  - Build and Test both microservices: 
  -- To build jar for each of the microservices from their root directory (use either cygwin or Intelij terminal), run below command
  -- $ mvn clean install (Ensure mvn is on class path, If required export it to class path)
  -- $ mvn spring-boot:run (To start the application)
  -- OR $ java -jar target/cl-customers-0.0.1-SNAPSHOT.jar (To start the application)
  -
  - (a) Customers: Steps to dockerize and run :
  - -----------------------------------------------
  -- Step 1. Create Application Jar file
  --- $ mvn clean install
  -- Step 2. Write Docker file
  --- see *\CustomerLoans\feature-2\customers\Dockerfile
  -- Step 3. Build Docker the image
  --- $ docker build . -t gmdjaveed/cl-customers:f2  (Note: Use your own docker repository name)
  -- Step 4. Run Docker
  --- $ docker run -d -p 8080:8080 gmdjaveed/cl-customers:f2
  -- Step 5. Push image to docker hub
  --- $ docker push gmdjaveed/cl-customers:f2
  -
  -- Note ../customers/   (Note: you should have Dockerfile here)
  -- $ docker images (see your new image here)
  -- $ docker inspect <dd48- (see your repo name, author, java version etc. Substitute with your image id)
  -- $ docker run -d -p 8080:8080 gmdjaveed/cl-customers:f2 (run the image in detach mode)
  -- $ docker run -d -p 8081:8080 gmdjaveed/cl-customers:f2 (run 2nd container if required.)
  -- Note: <node-port>:should be different, <container-port> could have same port since each container has different network of its own!
  -- Port Mapping/Forwarding/Publishing: request -> Local network[port] -> Docker network[port]
  -- $ docker start/stop <container-id-  -- $ docker ps (To checkout running containers)
  -- $ docker push gmdjaveed/cl-customers:f2
  -
  -   (b) Loans: Steps to dockerize and run :
  - -----------------------------------------------
  -- Step 1. Include the below maven plugin in pom.xml
    (Note: Use your own docker repository name inside pom.xml)
    <build>
      <plugins>
        <plugin>
		  <groupId>org.springframework.boot</groupId>
		  <artifactId>spring-boot-maven-plugin</artifactId>
		  <configuration>
		    <image>
		      <name>gmdjaveed/${project.artifactId}:f2</name>
		    </image>
		  </configuration>
		</plugin>
	  </plugins>
    </build>	
  -- Step 2. Build Docker the image using buildpack
  --- $ mvn spring-boot:build-image   
  -- Step 3. Check the newly created docker images and size 
  --- $ docker images (buildpack cl-loans size < cl-customers image i.e. optimized, compressed)
  -- Step 4. Run Docker
  --- $ docker run -d -p 8090:8090 gmdjaveed/cl-loans:f2
  -- Step 5. Push image to docker hub
  --- $ docker push gmdjaveed/cl-loans:f2
  -- Step 6. Check out running containers.
  -- $ docker ps (To checkout running containers)
  
  > Workspace:
  > ----------- 
```

### Credit:
```
The credit for building these microservices applications with various features goes to Udemy tutor Madan Reddy for his 
course '[NEW] Master Microservices with SpringBoot,Docker,Kubernetes' Develop Microservices with Java, Spring Boot, 
Spring Cloud, Docker, Kubernetes, Helm, Microservices Security'

Course URL : https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/
```