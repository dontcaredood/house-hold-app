# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

# Ensure the mvnw script is executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Create a group and user to run the app with a specific UID and GID
RUN groupadd -g 10001 appgroup && useradd -r -u 10001 -g appgroup appuser

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file from the build stage
COPY --from=build /app/target/we-shoot-app-rest-0.0.1-SNAPSHOT.jar /app/we-shoot-app-rest.jar

# Change ownership of the app directory
RUN chown -R appuser:appgroup /app

# Switch to the non-root user with UID 10001
USER 10001

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "we-shoot-app-rest.jar"]
