#!/bin/bash

CODEDIR=/Users/fredrik/dev/workspaces/gae

CLASSPATH=$CODEDIR/jhacks/bin

MAINCLASS=se.subsurfers.utils.NewMemberUtil

java -cp $CLASSPATH $MAINCLASS $ARGS "$@"
