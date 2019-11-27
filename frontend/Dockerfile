FROM node:12.2.0-alpine as build
WORKDIR /app
COPY package.json /app/package.json
RUN yarn install
COPY . /app
RUN yarn build

FROM nginx:1.17-alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]