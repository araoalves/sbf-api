FROM openjdk:15
COPY target/sbf-api.jar sbf-api.jar
EXPOSE 9500
ENTRYPOINT ["java", "-jar", "sbf-api.jar"]