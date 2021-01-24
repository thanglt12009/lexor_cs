@echo off
call mvn clean package
call docker build -t com.lexor/lexor_cs .
call docker rm -f lexor_cs
call docker run -d -p 9080:9080 -p 9443:9443 --name lexor_cs com.lexor/lexor_cs