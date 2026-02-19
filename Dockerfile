# Use Eclipse Temurin (OpenJDK) 21 for runtime
FROM eclipse-temurin:21-jdk-jammy AS build

# Set working directory
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

#Runtime stage
FROM eclipse-temurin:21-jdk-jammy
ARG PROFILE=prod
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /app/target/*.jar /app/

EXPOSE 8081

ENV DB_URL=jdbc:postgresql://localhost:5432/mydb

ENV DB_URL=jdbc:postgresql://postgres-sql-spring-app:5432/spring_app_db

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} spring-security-asymmetric-encryption-${JAR_VERSION}.jar