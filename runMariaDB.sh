#!/usr/bin/env bash

set -e

if [ "$(docker network ls | grep -c 'mariadb-local')" -le 0 ];
then
  echo 'Docker network mariadb-local does not exist. Start create mariadb docker network'
  echo 'Start create mariadb docker network'
  docker network create mariadb-local
else
  echo 'Docker network mariadb-local exists'
fi

docker rmi "$(docker images -f "dangling=true" -q)" 2> /dev/null || true
docker stop mariadb-sample 2> /dev/null || true
docker rm mariadb-sample 2> /dev/null || true

docker pull mariadb:10.6.4
docker run -d -p 3306:3306  --name mariadb-sample --network=mariadb-local -e MARIADB_ROOT_PASSWORD=mypass -e MARIADB_DATABASE=localdb -e MARIADB_USER=mari -e MARIADB_PASSWORD=maripass -d mariadb:10.6.4