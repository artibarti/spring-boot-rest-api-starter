FROM openjdk:11.0.7-jre-slim
CMD mkdir /opt/spring-boot-rest-api-starter
WORKDIR /opt/spring-boot-rest-api-starter
COPY web/target/web-1.0-SNAPSHOT.jar ./spring-boot-rest-api-starter.jar
ENTRYPOINT java -Dspring.datasource.url=jdbc:postgresql://spring-boot-rest-api-starter-db:5432/${POSTGRES_DB} \
           -Dspring.datasource.username=${POSTGRES_USER} \
           -Dspring.datasource.password=${POSTGRES_PASSWORD} \
           -Dspring.profiles.active=${ACTIVE_PROFILE} \
           ${JVM_OPTS} \
           -jar ./spring-boot-rest-api-starter.jar