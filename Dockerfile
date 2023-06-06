FROM --platform=linux/x86_64 eclipse-temurin:17-jre-alpine
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/demo01-0.0.1-SNAPSHOT.jar demo01.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar demo01.jar
