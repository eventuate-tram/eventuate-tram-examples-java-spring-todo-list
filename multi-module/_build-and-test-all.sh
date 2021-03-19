#! /bin/bash -e


dockerinfrastructure="./gradlew ${database}infrastructureCompose"
dockerall="./gradlew ${database}Compose"

${dockerall}Down
${dockerinfrastructure}Build
${dockerinfrastructure}Up

#Testing db cli
if [ "${database}" == "mysql" ]; then
  echo 'show databases;' | ./mysql-cli.sh -i
elif [ "${database}" == "postgres" ]; then
  echo '\l' | ./postgres-cli.sh -i
else
  echo "Unknown Database"
  exit 99
fi

./gradlew -x :end-to-end-tests:test build

${dockerall}Build
${dockerall}Up

./gradlew :end-to-end-tests:cleanTest :end-to-end-tests:test

${dockerall}Down
