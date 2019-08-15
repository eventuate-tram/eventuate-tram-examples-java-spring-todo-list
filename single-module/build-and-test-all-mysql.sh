#! /bin/bash -e


. ./set-env.sh

docker-compose -f docker-compose-mysql.yml down

docker-compose -f docker-compose-mysql.yml up -d --build elasticsearch
docker-compose -f docker-compose-mysql.yml up -d --build mysql

./wait-for-mysql.sh

docker-compose -f docker-compose-mysql.yml up -d --build cdcservice

./wait-for-infrastructure.sh

./gradlew build

docker-compose -f docker-compose-mysql.yml down