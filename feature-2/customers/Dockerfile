#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER javeed.nodomain.com

# Add the application's jar to the image
COPY target/cl-customers-0.0.1-SNAPSHOT.jar cl-customers-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "cl-customers-0.0.1-SNAPSHOT.jar"]