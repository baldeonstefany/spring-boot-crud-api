# ETAPA 1: build con Java 21
FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app

# Copiamos TODO el proyecto
COPY . .

# Compilamos
RUN mvn clean package -DskipTests

# ETAPA 2: runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=0 /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]



