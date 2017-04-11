FROM maven:3.3.9-jdk-8
ADD rest-web-app / 
RUN mvn package
RUN -p 8080:8080 -p 50000:50000 jenkins
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/rest-web-app-1.0.0-swarm.jar"]
