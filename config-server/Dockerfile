FROM maven:3-jdk-11 as builder

RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

RUN mvn -T 1.5C -B dependency:resolve dependency:resolve-plugins

COPY src /build/src

RUN mvn -T 1.5C -Dmaven.test.skip=true package

FROM openjdk:11-slim as runtime
EXPOSE 8080

ENV APP_HOME /app

ENV JAVA_OPTS=""

RUN mkdir $APP_HOME

RUN mkdir $APP_HOME/config

RUN mkdir $APP_HOME/log

VOLUME $APP_HOME/log
VOLUME $APP_HOME/config
WORKDIR $APP_HOME
#Copy executable jar file from the builder image
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]