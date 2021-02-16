#!/bin/sh

docker container stop usermanagement
docker container rm usermanagement
docker image rm usermanagement:latest

