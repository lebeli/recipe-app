FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
COPY . /backend/
WORKDIR /backend/
RUN mvn clean install -DskipTests

FROM java:8
EXPOSE 8080
COPY --from=MAVEN_BUILD /backend/demo_images/ ./demo_images/
COPY --from=MAVEN_BUILD /backend/target/gs-rest-service-0.1.0.jar ./
COPY ./wait-for-it.sh ./
RUN ["chmod", "+x", "./wait-for-it.sh"]

ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "gs-rest-service-0.1.0.jar"]
