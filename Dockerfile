# Step 1: Use an official OpenJDK runtime image
FROM eclipse-temurin:21-jre-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the built JAR file into the container
# For Maven use: target/*.jar | For Gradle use: build/libs/*-SNAPSHOT.jar
COPY target/*.jar app.jar

# Step 4: Expose your custom Spring Boot port
EXPOSE 5048

# Step 5: Execute the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
