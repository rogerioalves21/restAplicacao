FROM java:openjdk-8-jdk

RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rest-web-app/target/rest-web-app-1.0.0-swarm.jar"]
