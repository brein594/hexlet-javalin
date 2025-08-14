FROM gradle:8.12.1-jdk21

WORKDIR /main

COPY /main .

RUN ["./gradlew", "clean", "build"]

CMD ["./gradlew", "run"]