# --- Stage 1: Build the Maven Project inside Render ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy configuration files and source code
COPY pom.xml .
COPY src ./src

# Compile the application and skip tests to speed up the process
RUN mvn clean package -DskipTests

# --- Stage 2: Create the final lean runtime container ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the compiled JAR straight from the build stage above
COPY --from=build /app/target/*.jar app.jar

EXPOSE 5048
ENTRYPOINT ["java", "-jar", "app.jar"]
