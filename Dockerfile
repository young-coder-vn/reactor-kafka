FROM openjdk:11.0-jre-slim-buster
ENV TZ="Asia/Ho_Chi_Minh"
RUN mkdir /opt/app
COPY build/libs/kafka-0.0.1-SNAPSHOT.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]