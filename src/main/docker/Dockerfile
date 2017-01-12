FROM java:latest
VOLUME /tmp
ADD app.jar
RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]