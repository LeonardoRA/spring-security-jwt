FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

add target/security.Accounts-0.0.1-SNAPSHOT.jar Accounts-0.0.1.jar
entrypoint ["java", "-jar", "Accounts-0.0.1.jar"]