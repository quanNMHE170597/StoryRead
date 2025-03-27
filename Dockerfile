# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# ✅ Copy cả file DB vào đúng vị trí (nếu cần build gì từ đó)
RUN mkdir -p src/main/resources/db
COPY src/main/resources/db/ReadingStoryDB.sqlite src/main/resources/db/

RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app

# ✅ Copy file DB vào container
COPY --from=build /app/src/main/resources/db/ReadingStoryDB.sqlite ./src/main/resources/db/

# ✅ Copy file jar
COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
