FROM maven:3.3.9-jdk-8
ADD pom.xml /opt/rest_aplicacao/
ADD rest-web-app /opt/rest_aplicacao/
RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/rest-web-app/target/rest-web-app-1.0.0-swarm.jar"]
