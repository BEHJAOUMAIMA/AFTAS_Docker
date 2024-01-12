FROM node:18-alpine as angular
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build --prod

FROM nginx:alpine
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf
COPY --from=angular /app/dist/aftas/browser /usr/share/nginx/html
