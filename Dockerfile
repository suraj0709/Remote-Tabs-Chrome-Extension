FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage using a slim version of OpenJDK 17
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/remoteTabs-0.0.1-SNAPSHOT.jar remoteTabs.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","remoteTabs.jar"]
