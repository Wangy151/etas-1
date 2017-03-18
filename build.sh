#!/bin/bash

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