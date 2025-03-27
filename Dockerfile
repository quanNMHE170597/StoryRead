# Dùng image Maven + JDK để build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Build ứng dụng Spring Boot (tạo file jar)
RUN mvn clean package -DskipTests

# Stage 2: dùng image nhỏ để chạy jar
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy file jar từ stage build sang stage run
COPY --from=build /app/target/*.jar app.jar

# Chạy ứng dụng
CMD ["java", "-jar", "app.jar"]
