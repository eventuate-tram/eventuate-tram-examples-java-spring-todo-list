#! /bin/bash -e

./gradlew assemble

docker-compose up -d --build mysql

./wait-for-mysql.sh

docker-compose up -d --build zookeeper
docker-compose up -d --build kafka
docker-compose up -d --build eventuatelocalcdcservice
docker-compose up -d --build tramcdcservice

./wait-for-infrastructure.sh

docker-compose up -d --build

./wait-for-todo-list-services.sh