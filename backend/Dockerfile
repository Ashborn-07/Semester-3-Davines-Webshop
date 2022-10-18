FROM azul/zulu-openjdk-alpine:17 
ARG JAR_FILE=target/build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"
