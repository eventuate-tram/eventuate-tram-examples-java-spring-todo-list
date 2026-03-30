#! /bin/sh

until docker exec multi-module-mysql-1 sh -c 'mysql -uroot -prootpassword -e "select 1"' > /dev/null 2>&1
do
 echo sleeping for mysql
 sleep 5
done
