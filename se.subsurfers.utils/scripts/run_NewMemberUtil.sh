#!/bin/bash
#set -xv

CODEDIR=$(dirname $(dirname $0))

CLASSPATH=$CODEDIR/bin

MAINCLASS=se.subsurfers.utils.NewMemberUtil

java -cp $CLASSPATH $MAINCLASS $ARGS "$@"
