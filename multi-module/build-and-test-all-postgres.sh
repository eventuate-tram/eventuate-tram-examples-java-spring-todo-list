#! /bin/bash -e

export database=postgres
export SPRING_PROFILES_ACTIVE=postgres

./_build-and-test-all.sh
