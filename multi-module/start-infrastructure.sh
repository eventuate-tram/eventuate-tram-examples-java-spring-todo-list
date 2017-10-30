#! /bin/bash -e

docker-compose -f docker-compose-infrastructure.yml up -d --build mysql

./wait-for-mysql.sh

docker-compose -f docker-compose-infrastructure.yml up -d --build

./wait-for-infrastructure.sh