FROM maven:3.9.1-eclipse-temurin-17 AS build

COPY . /usr/src/

RUN mvn -f /usr/src/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-alpine

COPY --from=build /usr/src/target/jacx-0.0.1-SNAPSHOT.jar /usr/src/

WORKDIR /usr/src/

EXPOSE 8080

CMD ["java", "-jar", "jacx-0.0.1-SNAPSHOT.jar"]