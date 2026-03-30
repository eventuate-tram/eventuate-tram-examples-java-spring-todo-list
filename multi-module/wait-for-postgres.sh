#! /bin/sh

until docker exec multi-module-postgres-1 sh -c 'PGPASSWORD=eventuate psql -h localhost -U eventuate -c "select 1"' > /dev/null 2>&1
do
 echo sleeping for postgres
 sleep 5
done
