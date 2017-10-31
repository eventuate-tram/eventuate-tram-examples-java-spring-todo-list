#! /bin/bash -e


. ./set-env.sh

docker-compose -f docker-compose-infrastructure.yml down

./start-infrastructure.sh

./gradlew clean
./gradlew build

docker-compose -f docker-compose-infrastructure.yml down