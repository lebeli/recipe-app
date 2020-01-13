## STAGE 1: Build React Application 
FROM node:8.11.1 as frontend-builder 
COPY . /frontend
WORKDIR /frontend

RUN rm -rf node_modules
RUN npm i npm@latest -g
RUN npm install 
RUN npm start 
# RUN npm run build

# ## STAGE 2: Run nginx 
# FROM nginx 
# COPY --from=frontend-builder /frontend/dist/* /usr/share/nginx/html/dist/
# COPY --from=frontend-builder /frontend/public/* /usr/share/nginx/html/
# EXPOSE 80