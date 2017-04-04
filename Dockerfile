FROM openjdk:jre-alpine

ADD rest-web-app-1.0.0-swarm.jar /target/rest-web-app-1.0.0-swarm.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/target/wildfly-swarm.jar"]
