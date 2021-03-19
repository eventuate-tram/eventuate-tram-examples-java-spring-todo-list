#! /bin/bash -e

dockerall="./gradlew ${database}Compose"
dockerinfrastructure="./gradlew ${database}infrastructureCompose"

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

./gradlew build

${dockerall}Down

