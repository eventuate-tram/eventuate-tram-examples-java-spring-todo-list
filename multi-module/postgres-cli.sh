#! /bin/bash -e

docker run $* \
   --name postgresterm --network=${PWD##*/}_default \
   --rm postgres:9.6.5 \
   sh -c 'export PGPASSWORD=eventuate; exec psql -h postgres -U eventuate'
