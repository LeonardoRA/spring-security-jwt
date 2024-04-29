FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

env database_url jdbc:mysql://localhost:3306/accounts?useSSL=false&serverTimezone=UTC
env database_user root
env database_pass LEO
env database_plataform org.hibernate.dialect.MySQLDialect
env database_driver com.mysql.cj.jdbc.Driver

CMD ["mvn", "install"]
add target/Accounts-0.0.1-SNAPSHOT.jar Accounts-0.0.1.jar
entrypoint ["java", "-jar", "Accounts-0.0.1.jar"]