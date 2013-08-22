#!/bin/bash
#
# check_for_existing_email.sh new_email_file old_email_file
#
#set -xv
IFS="
"

cat << EOT

NOTE: If weird results, run "dos2unix" on the old email file first..."

EOT

PROMPT=yes
if [ "$1" = "-q" ]
then
	PROMPT=no
	shift
fi

if [ $# -ne 2 ]
then
	cat << EOT
Usage: $(basename $0) new_email_file old_email_file

EOT
	exit
fi

new_email_file=$1
old_email_file=$2

for i in $(cat $new_email_file | grep @)
do
	#echo "Checking [$i] ..."
	if grep -q "$i" $old_email_file
	then 
		echo "Exists: $i"
		if [ "$PROMPT" = "yes" ]
		then
			read a
		fi
echo "---"
	fi
done
