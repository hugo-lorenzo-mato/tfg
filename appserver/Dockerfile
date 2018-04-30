#FROM tomcat:8.0-jre8
FROM tomcat:9.0.7-jre8
#FROM tomcat:7

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./application/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war


## Ejemplo de config mediante variables de entorno:
#ENV JAVA_OPTS="-Ddatasource.jdbcUrl=jdbc:postgresql://pg-d:5432/ggal1701 -Ddatasource.user=postgres -Ddatasource.password=postgres"


# tomcat-users.xml sets up user accounts for the Tomcat manager GUI
#ADD tomcat/tomcat-users.xml $CATALINA_HOME/conf/
# Acceder a tomcat desde fuera de la propia máquina
#ADD tomcat/context.xml $CATALINA_HOME/webapps/manager/META-INF/

# ADD tomcat/catalina.sh $CATALINA_HOME/bin/
#ADD tomcat/run.sh $CATALINA_HOME/bin/run.sh
#RUN chmod +x $CATALINA_HOME/bin/run.sh

#RUN echo "export JAVA_OPTS=\"-Ddatasource.jdbcUrl=jdbc:postgresql://mypostgres:5432/ggal1701\"" > /usr/local/tomcat/bin/setenv.sh

# add postgres JDBC driver jar
#ADD tomcat/postgresql-42.2.2.jar $CATALINA_HOME/lib/

# add tomcat jpda debugging environmental variables
#ENV JPDA_ADDRESS="8000"
#ENV JPDA_TRANSPORT="dt_socket"


# start tomcat8 with remote debugging
#EXPOSE 8080
#CMD ["run.sh"]
