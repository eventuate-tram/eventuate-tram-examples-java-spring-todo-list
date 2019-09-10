#! /bin/bash -e


. ./set-env-${database}.sh

dockerinfrastructure="./gradlew ${database}infrastructureCompose"
dockerall="./gradlew ${database}Compose"

${dockerall}Down
${dockerinfrastructure}Build
${dockerinfrastructure}Up

./wait-for-infrastructure.sh

./gradlew -x :end-to-end-tests:test build

${dockerall}Build
${dockerall}Up

./wait-for-todo-list-services.sh

./gradlew :end-to-end-tests:cleanTest :end-to-end-tests:test

${dockerall}Down
