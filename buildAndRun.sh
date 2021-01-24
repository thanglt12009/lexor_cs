#!/bin/sh
mvn clean package && docker build -t com.lexor/lexor_cs .
docker rm -f lexor_cs || true && docker run -d -p 9080:9080 -p 9443:9443 --name lexor_cs com.lexor/lexor_cs