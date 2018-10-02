FROM openjdk:8-jre-alpine
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*
MAINTAINER Tomasz Ceszke
VOLUME /tmp
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/share/ml-login/login-service.jar
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/ml-login/login-service.jar