FROM openjdk:17-slim AS build

WORKDIR application

LABEL maintainer="Artyom Zheltyshev <artem.zheltyshev.98@gmail.com>"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract


FROM openjdk:17-slim

VOLUME /tmp

WORKDIR application

COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]