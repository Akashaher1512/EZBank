#start with base image containing java runtime
FROM openjdk:17-jdk-slim

#Information around who is maintin this image
MAINTAINER akash

#add the applications jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]
