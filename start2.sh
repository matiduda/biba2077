#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$DIR"
cd ..

if [ "${OSTYPE//[0-9.]/}" == "darwin" ]
then
	PLATFORM="mac"
else
	PLATFORM="linux"
fi

CLASSPATH=$(JARS=("$(pwd)/libs"/*.jar "$(pwd)/jfx/$PLATFORM/"*.jar); IFS=:; echo "${JARS[*]}")
JPROCLASSPATH=$(JARS=("$(pwd)/jprolibs"/*.jar); IFS=:; echo "${JARS[*]}")
JPRO_ARGS=("-Xmx1000m" "-Djpro.applications.default=com.jpro.hellojpro.HelloJProFXML" "-Dprism.useFontConfig=false" "-Djpro.forceShutdown=true" "-Dhttp.port=80" "-Dquantum.norenderjobs=true" "-Dglass.platform=Monocle" "-Dmonocle.platform=Headless" "-Dprism.fontdir=fonts/" "-Djpro.deployment=GRADLE-Distribution")



java "${JPRO_ARGS[@]}" "-Djprocp=$JPROCLASSPATH" "$@" -cp "$CLASSPATH"  com.jpro.boot.JProBoot