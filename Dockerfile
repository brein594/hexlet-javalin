FROM gradle:8.12.1-jdk21

WORKDIR /src

COPY /src .

RUN ["./gradlew", "clean", "build"]

CMD ["./gradlew", "run"]