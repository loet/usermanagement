#!/bin/sh

mvn clean install
docker cp target/usermanagement-1.0-SNAPSHOT.war usermanagement:/opt/jboss/wildfly/standalone/deployments/usermanagement.war
