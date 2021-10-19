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

docker pull mariadb
docker run -d -p 127.0.0.1:3306:3306  --name mariadb-sample -e MARIADB_ROOT_PASSWORD=mypass -d mariadb:10.6.4