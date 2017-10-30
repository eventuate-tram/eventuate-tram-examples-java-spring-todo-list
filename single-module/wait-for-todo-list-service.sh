#! /bin/bash

done=false

host=$DOCKER_HOST_IP
ports="8080"

while [[ "$done" = false ]]; do
	for port in $ports; do
		curl -q http://${host}:${port}/health >& /dev/null
		if [[ "$?" -eq "0" ]]; then
			done=true
		else
			done=false
			break
		fi
	done
	if [[ "$done" = true ]]; then
		echo the todo list service is started
		break;
  fi
	echo -n .
	sleep 1
done
