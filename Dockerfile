# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY backend/mvnw backend/pom.xml ./
COPY backend/.mvn .mvn

# Copy the source code
COPY backend/src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Copy the built jar file
RUN cp target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
