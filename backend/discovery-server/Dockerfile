# Use an official Java 17 runtime as a parent image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file to the container
# We use a wildcard so we don't have to hardcode the exact version number
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]