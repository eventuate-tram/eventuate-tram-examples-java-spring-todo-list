#! /bin/bash -e


. ./set-env-postgres.sh

docker-compose -f docker-compose-infrastructure-postgres.yml down

./start-infrastructure-postgres.sh

./gradlew build

docker-compose -f docker-compose-infrastructure-postgres.yml down