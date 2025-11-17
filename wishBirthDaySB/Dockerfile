# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests


# Stage 2: Create final image using OpenJDK
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Set Timezone (e.g., Asia/Kolkata)
ENV TZ=Asia/Kolkata
RUN apt-get update && apt-get install -y tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
    apt-get clean

# Copy the built JAR file from the build stage
COPY --from=build /app/target/wishBirthDaySB-0.0.1-SNAPSHOT.jar .

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/wishBirthDaySB-0.0.1-SNAPSHOT.jar"]
