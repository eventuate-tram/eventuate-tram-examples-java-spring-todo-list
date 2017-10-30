#! /bin/bash -e


. ./set-env.sh

docker-compose down

./start-infrastructure.sh

./gradlew clean
./gradlew build

docker-compose down