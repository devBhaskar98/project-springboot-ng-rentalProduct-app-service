# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies (this will be cached unless the pom.xml changes)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the rest of the project files and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/rentalproduct-1.0.0.jar /app/rentalproduct.jar

# Expose the port on which the application will run
EXPOSE 8080

# Set environment variables for PostgreSQL connection (default values)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://rental-product-db:5432/rental_product
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin@123

# Run the jar file
ENTRYPOINT ["java", "-jar", "rentalproduct.jar"]
