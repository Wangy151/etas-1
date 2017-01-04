#!/bin/sh
APPLICATION=portal.jar
SPRING_CONFIG_FILE=../conf/application.properties
ENDPOTIN_CONFIG_FILE=file:../conf/endpoint.json
MAX_MEMORY=2048M
MAX_PERM_MEMORY=512M

find /home/work/portal -type d -name ".svn" |xargs rm -rf {}
nohup java -Dspring.config.location=$SPRING_CONFIG_FILE -Dendpoint.config=$ENDPOTIN_CONFIG_FILE -Dfile.encoding=UTF-8 \
-Xmx$MAX_MEMORY -XX:MaxPermSize=$MAX_PERM_MEMORY -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps \
-Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=20M -jar $APPLICATION > /dev/null 2>&1 &