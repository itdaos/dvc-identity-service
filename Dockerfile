FROM openjdk:18-jdk-alpine
ENV APP_FILE identity-0.0.1-SNAPSHOT.jar
ENV APP_HOME /app
EXPOSE 4444
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]