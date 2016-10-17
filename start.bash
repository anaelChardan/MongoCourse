#!/bin/bash

docker-compose up -d --build
docker exec -it mongocourse_mongodb_1 sh
