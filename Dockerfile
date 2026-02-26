############################
# 1️⃣ Build Stage
############################
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom first for caching dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests


############################
# 2️⃣ Runtime Stage
############################
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy generated jar
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]