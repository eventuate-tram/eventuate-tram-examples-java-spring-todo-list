#! /bin/bash -e


. ./set-env-postgres.sh

docker-compose -f docker-compose-postgres.yml down
docker-compose -f docker-compose-postgres.yml up -d --build elasticsearch
docker-compose -f docker-compose-postgres.yml up -d --build postgres

./wait-for-postgres.sh

docker-compose -f docker-compose-postgres.yml up -d --build tramcdcservice

./wait-for-infrastructure.sh

./gradlew -x :end-to-end-tests:test build

docker-compose -f docker-compose-postgres.yml up -d --build

./wait-for-todo-list-services.sh

./gradlew :end-to-end-tests:cleanTest :end-to-end-tests:test

docker-compose -f docker-compose-postgres.yml down
