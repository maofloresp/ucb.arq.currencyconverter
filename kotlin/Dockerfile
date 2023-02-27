FROM eclipse-temurin:11
ENV DB_HOST=192.168.0.2
COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]