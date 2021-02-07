#!/usr/bin/env bash

cd ..
BASE_DIR=.
releaseFileNAME=release-note
VERSION=`cat ${BASE_DIR}/${releaseFileNAME} | grep Version | cut -d= -f2 | head -n 1`

JAVA_EXE=java
JAVA_OPT="-Xmx1024m -Xms256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/ -Djava.security.egd=file:///dev/urandom -Dname=tool"
MAIN_CLASS=ApplicationMain


for f in ${BASE_DIR}/lib/*.jar;
do
    CLASSPATH=${CLASSPATH}:${f}
done

echo "version ${VERSION}"

${JAVA_EXE} ${JAVA_OPT} -classpath ${CLASSPATH} ${MAIN_CLASS} "$@"