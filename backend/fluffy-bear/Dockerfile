FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
RUN mvn install -DskipTests

FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD --from=MAVEN_BUILD /target/gs-rest-service-0.1.0.jar gs-rest-service-0.1.0.jar

ENTRYPOINT ["java","-jar","gs-rest-service-0.1.0.jar"]
