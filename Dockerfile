FROM jboss/wildfly
MAINTAINER Rogerio Alves Rodrigues "rogerioalves21@gmail"
 
ADD rest-web-app-1.0.0.war /opt/jboss/wildfly/standalone/deployments/

