rm /usr/share/nginx/html/configuration.json

if [ "$PROXY_BACKEND" = "true" ]; then
      cp /app/default.conf /etc/nginx/conf.d/default.conf
      sed -i -e "s|__BACKEND_URL__|$BACKEND_URL|g" /etc/nginx/conf.d/default.conf

      echo "{\"baseURL\": \"/api\"}" > /usr/share/nginx/html/configuration.json
    else
      echo "{\"baseURL\": \"$BACKEND_URL\"}" > /usr/share/nginx/html/configuration.json
fi

nginx -g 'daemon off;'
