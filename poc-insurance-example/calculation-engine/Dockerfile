FROM openjdk:16-jdk-alpine
RUN apk --no-cache add curl
COPY target/calculation-engine*.jar calculation-engine-1.0.jar
CMD java ${JAVA_OPTS} -jar calculation-engine-1.0.jar
ENV KAFKA_BROKER_LISTENER $KAFKA_BROKER_LISTENER
ENV DYNAMODB_HOST $DYNAMODB_HOST
ENV MARKET_SERVICE_HOST $MARKET_SERVICE_HOST
RUN echo $KAFKA_BROKER_LISTENER