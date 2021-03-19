#! /bin/bash -e

CONTAINER_IDS=$(docker ps -a -q)

for id in $CONTAINER_IDS ; do
  docker kill $id || echo not running
  docker rm $id || echo disappeared
done
