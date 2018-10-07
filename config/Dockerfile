FROM openjdk:8-jdk-alpine
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*
MAINTAINER Tomasz Ceszke
VOLUME /tmp
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/share/ml-login/config.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/share/ml-login/config.jar"]
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/ml-login/config.jar