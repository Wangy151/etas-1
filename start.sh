#!/bin/sh

APPLICATION=etas.jar
SPRING_CONFIG_FILE=../conf/application.properties
# MAX_MEMORY=2048M
# MAX_PERM_MEMORY=512M

nohup java -Dspring.config.location=$SPRING_CONFIG_FILE -Dfile.encoding=UTF-8 -jar $APPLICATION > debug.log 2>&1 &