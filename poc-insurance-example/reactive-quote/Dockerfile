FROM amazoncorretto:17.0.1-alpine
RUN apk --no-cache add curl
COPY target/reactive-quote*.jar reactive-quote-1.0.jar
CMD java ${JAVA_OPTS} -jar reactive-quote-1.0.jar
ENV DYNAMODB_HOST $DYNAMODB_HOST
ENV AWS_ACCESS_KEY_ID $AWS_ACCESS_KEY_ID
ENV AWS_SECRET_ACCESS_KEY $AWS_SECRET_ACCESS_KEY