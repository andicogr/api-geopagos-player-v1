FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/api-player-v1-1.0.0-SNAPSHOT.jar api-player-v1.jar
EXPOSE 9920
ENTRYPOINT exec java $JAVA_OPTS -jar api-player-v1.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar configserver.jar
