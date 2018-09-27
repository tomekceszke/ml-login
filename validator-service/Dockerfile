FROM openjdk:8-jdk-alpine
MAINTAINER Tomasz Ceszke
VOLUME /tmp
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/share/ml-login/validator-service.jar
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/ml-login/validator-service.jar