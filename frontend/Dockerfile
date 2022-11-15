# download a base image
FROM node:16.17.1-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./
COPY package-lock.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# open the port on which the server will start
EXPOSE 3000

# run the front end server
CMD [ "npm", "start" ]





# # make the 'app' folder the current working directory
# COPY source dest