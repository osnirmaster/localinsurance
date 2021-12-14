FROM openjdk:16-jdk-alpine
RUN apk --no-cache add curl
COPY target/market*.jar market-1.0-SNAPSHOT.jar
CMD java ${JAVA_OPTS} -jar market-1.0-SNAPSHOT.jar
ENV KAFKA_BROKER_LISTENER $KAFKA_BROKER_LISTENER
ENV DYNAMODB_HOST $DYNAMODB_HOST
RUN echo $KAFKA_BROKER_LISTENER
