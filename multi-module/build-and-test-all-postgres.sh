#! /bin/bash -e

export database=mysql
export SPRING_PROFILES_ACTIVE=postgres

./_build-and-test-all.sh
