#!/usr/bin/env bash

set -e

if [ "$(docker network ls | grep -c 'postgres-local')" -le 0 ];
then
  echo 'Docker network postgres-local does not exist. Start create postgres docker network'
  echo 'Start create postgres docker network'
  docker network create postgres-local
else
  echo 'Docker network postgres-local exists'
fi

docker rmi "$(docker images -f "dangling=true" -q)" 2> /dev/null || true
docker stop some-postgres 2> /dev/null || true
docker rm some-postgres 2> /dev/null || true

docker pull postgres:14.0
docker run -d -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres:14.0