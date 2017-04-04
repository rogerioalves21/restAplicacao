FROM maven:3.3.9-jdk-8
ADD pom.xml / 
ADD rest-web-app / rest-web-app

RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/rest-web-app/target/rest-web-app-1.0.0-swarm.jar"]
