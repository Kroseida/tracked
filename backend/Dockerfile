FROM openjdk:17-alpine
RUN mkdir /app

COPY target/tracked.jar /app

# Add docker-compose-wait tool to wait for all containers to be up and running
# If you don't wan't to use this, you can build the image without it.
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["java", "-jar", "/app/tracked.jar"]
EXPOSE 8088
