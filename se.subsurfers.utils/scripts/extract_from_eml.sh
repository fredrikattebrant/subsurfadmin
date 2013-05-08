#!/bin/bash
#
# extract_from_eml
#
# extracts data from eml files
#
function usage()
{
	cat << EOH
	$(basename $0) 
		-e email
		-m mobil
		-f förnamn
		-l efternamn
		-g gata
		-p postnummer
		-s stad

	Dumpar vald info från *.eml

EOH
}

function append()
{
	if [ -n "$1" ]
	then
		echo "${1}|${2}"
	else
		echo $2
	fi
}


keys=""
col=2
field="-F:"

while [ $# -gt 0 ]
do
  case $1 in
	-e*)
		#extract email
		key="Email:"
		shift
		;;
	-m*)
		#extract mobil
		key="Mobilnr \(07XXXXXXXX\):"
		shift
		;;
	-f*)
		#extract first name
		key="Förnamn:"
		shift
		;;
	-l*)
		#extract last name
		key="Efternamn:"
		shift
		;;
	-g*)
		#extract street
		key="Gatuadress:"
		shift
		;;
	-p*)
		#extract zipcode
		key="Postnummer:"
		shift
		;;
	-s*)
		#extract city
		key="Postadress:"
		shift
		;;
	*)
		usage
		exit 1
		;;
  esac

  keys="$(append "${keys}" "${key}")"
done

for i in *.eml
do
	result="$(egrep -h "$keys" "$i" | pcol $field $col)"
	echo "$result"
done
