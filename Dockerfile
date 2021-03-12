FROM alpine:latest

WORKDIR /opt/guess_name

COPY ./target/guess_name.jar ./

RUN apk add openjdk11

CMD [ "java", "-jar", "guess_name.jar"]
