#! /bin/bash

export ports=

./_wait-for-services.sh $DOCKER_HOST_IP "8080"
