FROM node:10

COPY . /docs-keeper
RUN cd /docs-keeper && yarn

CMD cd ./docs-keeper && yarn start
