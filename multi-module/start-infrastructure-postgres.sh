#! /bin/bash -e

docker-compose -f docker-compose-infrastructure-postgres.yml up -d --build postgres

./wait-for-postgres.sh

docker-compose -f docker-compose-infrastructure-postgres.yml up -d --build

./wait-for-infrastructure.sh