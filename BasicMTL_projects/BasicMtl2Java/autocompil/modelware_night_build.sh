#!/usr/local/bin/tcsh
# $Id: modelware_night_build.sh,v 1.4 2003-12-18 15:26:26 dvojtise Exp $
# this script is run every night in order to verify that the latest files in the repository correctly compile
# it runs some tests on the compiler in order to assure non regression.
# sends email in case of trouble
# it copies correct distribution to the modelware.inria.fr web site

# BE CAREFUL : you need to contact Didier for every change in this script if you want to use it every night !!!
# 		the script cannot be checkout automaticaly as it is its role to checkout the latest version ...

# This scripts runs on Solaris at irisa.

#set ant environnment
source /udd/triskell/bin/multiplatform/apache-ant-1.5.4/env_ant.sh


cd $HOME/temp
#remove old version
echo removing old build...
\rm -r -f modelware_night_build
mkdir modelware_night_build
cd modelware_night_build

#checkout latest version from repository
setenv CVS_RSH ssh
# load the private key that have no passphrase (do not remove this line ! this is the way my script can run automatically)
# this suppose that you are running a ssh-agent 
ssh-add $HOME/.ssh/weak_identity
echo cvs -Q -d :ext:guest@lievre.irisa.fr:/CVS/modelware checkout all
cvs -Q -d :ext:guest@lievre.irisa.fr:/CVS/modelware checkout all
#cvs -d /udd/triskell/cvsroot checkout dev/MT/BasicMtl2Java
cd BasicMtl2Java
#setenv BASE `pwd`
setenv BASE $HOME/temp/modelware_night_build
setenv CLASSPATH $BASE/Utils/ThirdParty/JUnit/junit.jar:$BASE/BasicMtlAntlr/ThirdParty/ANTLR/antlrfull.jar

#echo $CLASSPATH
ant |& tee $BASE/ant_BasicMtl2Java.log

# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep FAILED  $BASE/ant_BasicMtl2Java.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The build failed, tests not run, please have a look in the log file: $BASE/ant_BasicMtl2Java.log " >msg.txt
   	echo "this may be normal for a short time, or someone may have forgotten to commit a file" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_BasicMtl2Java.log >> msg.txt   	
    cat msg.txt | Mail -s "[Modelware] Night build failed" modelware-cvs@irisa.fr
    exit
endif

cd ../CompilerTests/src
ant "build all" "run all" |& tee $BASE/ant_CompilerTests.log
# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep FAILED $BASE/ant_CompilerTests.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The Compiler tests failed, please have a look in the log file: $BASE/ant_CompilerTests.log " > msg.txt
   	echo "however the compiler was succefully compiled" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_CompilerTests.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build compiler tests failed" modelware-cvs@irisa.fr
    exit
endif

cd ../../UML1.4
ant |& tee $BASE/ant_UML14.log
# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep FAILED $BASE/ant_UML14.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
	echo "The UML14 tests failed, please have a look in the log file: $BASE/ant_UML14.log " > msg.txt
	echo "however the compiler compilation and CompilerTests were successfull"	>> msg.txt
   	echo "---" >> msg.txt
	cat $BASE/ant_UML14.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build tests failed" modelware-cvs@irisa.fr
    exit
endif
rm /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/*.zip
cp $BASE/BasicMtl2Java/LibAssociation/dist/*.zip /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/
