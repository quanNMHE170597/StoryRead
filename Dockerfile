# Dùng image Java 17
FROM openjdk:17-jdk-slim

# Thư mục làm việc trong container
WORKDIR /app

# Copy file jar đã build vào container
COPY target/*.jar app.jar

# Chạy app Spring Boot
CMD ["java", "-jar", "app.jar"]
