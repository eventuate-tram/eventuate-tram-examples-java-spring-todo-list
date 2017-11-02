#! /bin/bash -e


. ./set-env.sh

docker-compose -f docker-compose-infrastructure-mysql.yml down

./start-infrastructure-mysql.sh

./gradlew build

docker-compose -f docker-compose-infrastructure-mysql.yml down