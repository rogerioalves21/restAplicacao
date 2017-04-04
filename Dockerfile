FROM java:openjdk-8-jdk

RUN mvn package

ADD rest-web-app/target/rest-web-app-1.0.0-swarm.jar /opt/rest-web-app-1.0.0-swarm.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/rest-web-app-1.0.0-swarm.jar"]
