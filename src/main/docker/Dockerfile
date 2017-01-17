FROM java:latest
RUN  mkdir /app
ADD app.jar /app/
WORKDIR /app
EXPOSE 8080
CMD java -jar app.jar