FROM openjdk:17-ea-11-jdk-slim as build

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar #build에 포함됨. jar만 만들어내고 테스트 코드 실행은 안하는 등

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
