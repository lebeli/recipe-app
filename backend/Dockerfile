FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
COPY . /backend/
WORKDIR /backend/
RUN mvn clean install -DskipTests

FROM java:8
EXPOSE 8080
COPY --from=MAVEN_BUILD /backend/demo_images/ ./demo_images/
COPY --from=MAVEN_BUILD /backend/target/gs-rest-service-0.1.0.jar ./

ENTRYPOINT ["java", "-jar", "gs-rest-service-0.1.0.jar"]