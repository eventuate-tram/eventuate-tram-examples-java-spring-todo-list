#! /bin/bash -e

. ./set-env-${database}.sh

dockerall="./gradlew ${database}Compose"
dockerinfrastructure="./gradlew ${database}infrastructureCompose"

${dockerall}Down

${dockerinfrastructure}Build
${dockerinfrastructure}Up

./wait-for-infrastructure.sh

./gradlew build

${dockerall}Down

