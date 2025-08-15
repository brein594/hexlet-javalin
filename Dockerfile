FROM gradle:8.12.1-jdk21

WORKDIR /src

COPY . .

RUN ["./gradlew", "build"]

CMD ["./gradlew", "run"]