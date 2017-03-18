#!/bin/sh

APPLICATION=etas.jar
SPRING_CONFIG_FILE=../conf/application.properties
MAX_MEMORY=2048M
MAX_PERM_MEMORY=512M

java -Dspring.config.location=$SPRING_CONFIG_FILE -Dfile.encoding=UTF-8 \
-Xmx$MAX_MEMORY -XX:MaxPermSize=$MAX_PERM_MEMORY -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps \
-Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=20M -jar $APPLICATION


