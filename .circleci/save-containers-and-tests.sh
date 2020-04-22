#! /bin/bash -e

mkdir -p ~/container-logs
docker ps -a > ~/container-logs/containers.txt
sudo bash -c 'find /var/lib/docker/containers -name "*-json.log" -exec cp {} ~/container-logs \;'
sudo bash -c 'find  ~/container-logs -type f -exec chown circleci {} \;'
