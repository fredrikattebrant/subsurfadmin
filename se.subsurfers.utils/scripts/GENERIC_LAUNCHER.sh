#!/bin/sh
#
# copy to your script dir
# ln -s GENERIC_LAUNCHER.sh yourscript.sh
#
# where "yourscript.sh" is located in the "cmd" dir below
#
# this allows you to invoke the scripts in the "cmd" dir from anywhere
# without setting up a path to the "cmd" dir
#
cmd="$HOME/git/subsurfadmin/se.subsurfers.utils/scripts/$(basename $0)"
if [ -x "$cmd" ]
then
	exec "$cmd" "$@"
else
	echo "*** Cannot execute $cmd"
fi
