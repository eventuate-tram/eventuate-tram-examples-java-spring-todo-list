#! /bin/bash -e


. ./set-env-postgres.sh

docker-compose -f docker-compose-postgres.yml down

docker-compose -f docker-compose-mysql.yml up -d --build elasticsearch
docker-compose -f docker-compose-postgres.yml up -d --build postgres

./wait-for-postgres.sh

docker-compose -f docker-compose-postgres.yml up -d --build cdcservice

./wait-for-infrastructure.sh

./gradlew build

docker-compose -f docker-compose-postgres.yml down