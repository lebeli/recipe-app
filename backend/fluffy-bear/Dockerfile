FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD

COPY pom.xml /fluffy-bear/backend/
COPY src /fluffy-bear/backend/src/

WORKDIR /fluffy-bear/backend
RUN mvn package

FROM openjdk:8-jdk-alpine
WORKDIR /fluffy-bear/backend

COPY --from=MAVEN_BUILD /fluffy-bear/backend/target/gs-rest-service-0.1.0.jar /fluffy-bear/backend

ENTRYPOINT ["java","-jar","gs-rest-service-0.1.0.jar"]
