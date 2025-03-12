# 1️⃣ Этап сборки (Build Stage)
FROM maven:3.9.9-eclipse-temurin-21 AS build  

# Копируем config.json в нужное место
COPY config.json /root/.docker/config.json

WORKDIR /app

# Копируем pom.xml и загружаем зависимости (кешируем, чтобы ускорить сборку)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Копируем исходники и собираем проект
COPY src ./src
RUN mvn package -DskipTests

# 2️⃣ Этап выполнения (Runtime Stage)
FROM eclipse-temurin:21-jdk-alpine  
WORKDIR /app

# Копируем JAR-файл из предыдущего контейнера
COPY --from=build /app/target/*.jar app.jar  

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]