#!/bin/sh
if [ $# -eq 0 ] ; then
  mvn -Dspring.profiles.active=test clean package -U
else
  if [ $1 = "-q" ] ; then
    mvn -Dmaven.test.skip=true clean package -U
  else
    echo "build.sh [-q]"
    exit 0
  fi
fi

if [ $? -ne 0 ] ; then
  echo "mvn package error"
  exit -1
fi

rm -rf output
mkdir output

mkdir -p output/bin
mkdir -p output/conf
cp portal/target/portal-version.jar output/bin/portal.jar
cp portal/deploy/start.sh output/bin
cp portal/deploy/stop.sh output/bin