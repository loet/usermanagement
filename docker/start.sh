#!/bin/sh

docker build --tag=usermanagement:latest .
docker run --name usermanagement -p 8080:8080 -p 9990:9990 -it usermanagement