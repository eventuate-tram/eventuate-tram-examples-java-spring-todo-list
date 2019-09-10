#! /bin/bash -e

. ./set-env-${database}.sh

docker="./gradlew ${database}infrastructureCompose"

${docker}Down

${docker}Build
${docker}Up

./wait-for-infrastructure.sh

./gradlew build

${docker}Down
