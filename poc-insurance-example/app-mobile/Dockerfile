FROM openjdk:16-jdk-alpine
RUN apk --no-cache add curl
COPY target/mobile*.jar mobile-1.0.jar
CMD java ${JAVA_OPTS} -jar mobile-1.0.jar
ENV REQUEST_QUOTE_INIT $REQUEST_QUOTE_INIT