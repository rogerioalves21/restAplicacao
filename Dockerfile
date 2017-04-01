FROM jboss/wildfly
MAINTAINER Rogerio Alves Rodrigues "rogerioalves21@gmail"
 
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

