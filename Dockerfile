FROM maven:3.3.9-jdk-8

# Install the Ubuntu packages.RUN 
RUN sudo apt-get update
RUN sudo apt-get install git

RUN git clone https://github.com/rogerioalves21/rest_aplicacao.git

RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rest-web-app/target/rest-web-app-1.0.0-swarm.jar"]
