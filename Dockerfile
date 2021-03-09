FROM openjdk
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/spring-docker-spotify.jar
WORKDIR /app
ENTRYPOINT java -jar spring-docker-spotify.jar
EXPOSE 8080