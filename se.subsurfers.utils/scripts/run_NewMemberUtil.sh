#!/bin/bash
#set -xv

if [ -z "$CODEDIR" ]
then
	CODEDIR=$HOME/git/subsurfadmin/se.subsurfers.utils
	CLASSPATH=$CODEDIR/bin
else
	CLASSPATH=$CODEDIR/bin
fi



MAINCLASS=se.subsurfers.utils.NewMemberUtil

java -cp $CLASSPATH $MAINCLASS $ARGS "$@"
