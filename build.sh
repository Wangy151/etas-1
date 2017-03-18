#!/bin/bash

# set environment
# Java
export JAVA_HOME=/usr/java/jdk1.8.0_121
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin

# maven
export M2_HOME=/opt/apache-maven-3.3.9
export PATH=$PATH:$M2_HOME/bin


mvn -Dmaven.test.skip=true clean package -U

if [ $? -ne 0 ] ; then
  echo "mvn package error"
  exit -1
fi

rm -rf output
mkdir output

mkdir -p output/etas/bin
mkdir -p output/etas/conf
mkdir -p output/etas/log
cp target/etas-1.0.jar output/etas/bin/etas.jar
cp start.sh output/etas/bin
cp stop.sh output/etas/bin
cp target/classes/application.properties output/etas/conf