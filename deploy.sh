#!/bin/sh

mvn clean install

cp target/usermanagement-1.0-SNAPSHOT.war wildfly-21.0.0.Final/standalone/deployments/usermanagement.war

echo "\nINFO: deployed war to Wildfly!\n"

