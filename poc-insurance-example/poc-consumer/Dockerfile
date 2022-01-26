FROM openjdk:16-jdk-alpine
RUN apk --no-cache add curl
COPY target/poc-consumer*.jar poc-consumer-1.0.jar
CMD java ${JAVA_OPTS} -jar poc-consumer-1.0.jar
ENV KAFKA_BROKER_LISTENER $KAFKA_BROKER_LISTENER
RUN echo $KAFKA_BROKER_LISTENER