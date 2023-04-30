FROM adoptopenjdk/openjdk11:alpine-jdk

WORKDIR /app

# Копирование исходного кода и pom.xml
COPY src src
COPY pom.xml .

# Сборка приложения с использованием Maven
RUN ./mvnw package

# Копирование собранного JAR-файла в контейнер
COPY target/*.jar app.jar

# Запуск приложения при старте контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
