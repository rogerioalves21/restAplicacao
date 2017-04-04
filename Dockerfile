FROM openjdk:jre-alpine

ADD /rest-web-app/target/rest-web-app-1.0.0-swarm.jar /opt/rest-web-app-1.0.0-swarm.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/wildfly-swarm.jar"]
