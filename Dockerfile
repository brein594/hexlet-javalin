FROM gradle:8.12.1-jdk21

WORKDIR /src

COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .
COPY /src .

RUN ./gradlew --no-daemon dependencies

RUN ["./gradlew", "build"]

CMD ["./gradlew", "run"]