# Dùng image Java 17
FROM openjdk:17-jdk-slim

# Tạo thư mục làm việc trong container
WORKDIR /app

# Copy file jar build sẵn vào container
COPY target/*.jar app.jar

# Chạy ứng dụng Spring Boot
CMD ["java", "-jar", "app.jar"]
