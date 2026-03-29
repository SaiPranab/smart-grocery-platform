# Use an official Java 17 runtime as a parent image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file
COPY target/*.jar app.jar

# Expose the port the app runs on (optional but good practice for documentation)
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
