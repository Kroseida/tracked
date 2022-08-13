FROM nginx:latest
RUN mkdir /app

COPY dist/spa /usr/share/nginx/html
COPY .docker/start.sh /app/start.sh
COPY .docker/default.conf /app/default.conf

RUN chmod +x /app/start.sh

CMD ["/app/start.sh"]
EXPOSE 8088
