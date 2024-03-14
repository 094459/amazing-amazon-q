FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy dependency list and application JAR file
COPY target/*.jar app.jar

# Run the application 
CMD ["java", "-jar", "app.jar"]
