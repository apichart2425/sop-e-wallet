FROM node:12-alpine AS build
WORKDIR /app
COPY package.json package.json
RUN yarn install
COPY . .
RUN yarn build

FROM alpine:3.10
RUN apk update && \
  apk add --no-cache libstdc++ libgcc ca-certificates && \
  rm -rf /var/cache/apk/*
WORKDIR /app
COPY --from=build /app/server ./server
RUN chmod +X ./server
EXPOSE 3000

CMD ./server