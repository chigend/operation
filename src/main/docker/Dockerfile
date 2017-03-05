FROM java:latest
RUN  mkdir /app
ADD app.jar /app/
WORKDIR /app
-Duser.timezone=GMT+8
EXPOSE 8080
CMD java -jar app.jar