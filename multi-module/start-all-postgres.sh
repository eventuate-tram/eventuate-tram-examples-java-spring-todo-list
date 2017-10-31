#! /bin/bash -e

./gradlew clean
./gradlew assemble

docker-compose -f docker-compose-postgres.yml  up -d --build postgres

./wait-for-postgres.sh

docker-compose -f docker-compose-postgres.yml up -d --build zookeeper
docker-compose -f docker-compose-postgres.yml up -d --build kafka
docker-compose -f docker-compose-postgres.yml up -d --build eventuatelocalcdcservice
docker-compose -f docker-compose-postgres.yml up -d --build tramcdcservice

./wait-for-infrastructure.sh

docker-compose -f docker-compose-postgres.yml up -d --build

./wait-for-todo-list-services.sh