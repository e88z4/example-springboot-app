FROM openjdk:11.0-jdk-slim-buster as build

ADD ./ /app
RUN cd /app && \
    ./gradlew clean build

FROM openjdk:11.0-jre-slim-buster
COPY --from=build /app/build/libs/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]