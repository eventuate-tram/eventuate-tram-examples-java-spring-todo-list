#! /bin/bash -e

./gradlew assemble

docker-compose -f docker-compose-mysql.yml up -d --build mysql

./wait-for-mysql.sh

docker-compose -f docker-compose-mysql.yml up -d --build tramcdcservice

./wait-for-infrastructure.sh

docker-compose -f docker-compose-mysql.yml up -d --build

./wait-for-todo-list-service.sh