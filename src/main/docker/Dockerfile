FROM java:latest
RUN  mkdir /app
ADD app.jar /app/
WORKDIR /app
EXPOSED 8080
CMD java -jar app.jar