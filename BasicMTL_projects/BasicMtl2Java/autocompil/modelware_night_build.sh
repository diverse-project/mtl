#!/usr/local/bin/tcsh
# $Id: modelware_night_build.sh,v 1.16 2004-10-26 09:54:16 dvojtise Exp $
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
mkdir modelware_night_build/eclipse
mkdir modelware_night_build/eclipse/workspace
cd modelware_night_build
# get needed plugins : Eclipse 3.0 and EMF 2.0.1
unzip /udd/triskell/Soft/eclipse/V3.0/eclipse-SDK-3.0-win32.zip *eclipse.core.runtime* *eclipse.ui.workbench* *osgi* -d .

unzip /udd/triskell/Soft/eclipse/plugin/EMF/emf-sdo-SDK-2.0.1.zip -d .

cd eclipse/workspace

#checkout latest version from repository
setenv CVS_RSH ssh
# load the private key that have no passphrase (do not remove this line ! this is the way my script can run automatically)
# this suppose that you are running a ssh-agent 
ssh-add $HOME/.ssh/weak_identity
echo cvs -Q -d :ext:dvojtise@lievre.irisa.fr:/CVS/modelware checkout all
cvs -Q -d :ext:dvojtise@lievre.irisa.fr:/CVS/modelware checkout all
#cvs -d /udd/triskell/cvsroot checkout dev/MT/BasicMtl2Java
cd BasicMtl2Java
#setenv BASE `pwd`
setenv BASE $HOME/temp/modelware_night_build/eclipse/workspace
setenv CLASSPATH $BASE/Utils/ThirdParty/JUnit/junit.jar:$BASE/BasicMtlAntlr/ThirdParty/ANTLR/antlrfull.jar


#copy the correct version files (according to eclipse and emf version)
pwd
ant -f VersionManagement/EclipseV3/3.0.1_build.xml "selectThisVersion" |& tee $BASE/ant_SelectVersion.log


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

cd ../org.inria.BasicMTL.runtime
ant -f copy_build.xml "get all" |& tee $BASE/ant_BasicMTLRuntime_copy.log
ant "build.jars" |& tee $BASE/ant_BasicMTLRuntime.log
# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep -i FAILED $BASE/ant_BasicMTLRuntime.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The build failed (org.inria.BasicMTL.runtime), tests not run, please have a look in the log file: $BASE/ant_BasicMTLRuntime.log " > msg.txt
   	echo "this may be normal for a short time, or someone may have forgotten to commit a file" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_BasicMTLRuntime.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build failed (org.inria.BasicMTL.runtime)" modelware-cvs@irisa.fr
    exit
endif


cd ../MDRDriver
ant -f main_build.xml "compile" |& tee $BASE/ant_MdrDriver.log
# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep -i FAILED $BASE/ant_MdrDriver.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The build failed (MDRDriver), tests not run, please have a look in the log file: $BASE/ant_MdrDriver.log " > msg.txt
   	echo "this may be normal for a short time, or someone may have forgotten to commit a file" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_MdrDriver.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build failed (MDRDriver)" modelware-cvs@irisa.fr
    exit
endif

cd ../ModFactDriver
ant -f main_build.xml "compile" |& tee $BASE/ant_ModFactDriver.log
# check for errors and send mail to admin
setenv ERRORS_IN_BUILD `grep -i FAILED $BASE/ant_ModFactDriver.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The build failed (MDRDriver), tests not run, please have a look in the log file: $BASE/ant_ModFactDriver.log " > msg.txt
   	echo "this may be normal for a short time, or someone may have forgotten to commit a file" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_ModFactDriver.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build failed (ModFactDriver)" modelware-cvs@irisa.fr
    exit
endif

cd ../CompilerTests
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
setenv ERRORS_IN_BUILD `grep ERROR $BASE/ant_CompilerTests.log`
if ( "$ERRORS_IN_BUILD" != "" ) then
   	echo "The Compiler tests raised messages with ERROR level, please have a look in the log file: $BASE/ant_CompilerTests.log " > msg.txt
   	echo "however the compiler was succefully compiled" >> msg.txt
   	echo "---" >> msg.txt
   	cat $BASE/ant_CompilerTests.log >> msg.txt
   	cat msg.txt | Mail -s "[Modelware] Night build compiler tests raised messages with ERROR level" modelware-cvs@irisa.fr
    exit
endif
\rm /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/*.zip
\rm /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/changelog.txt
cp $BASE/BasicMtl2Java/LibAssociation/dist/*.zip /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/
cp $BASE/BasicMtl2Java/LibAssociation/dist/changelog.txt /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/
ln -s /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/*.zip /site/w3e/WWW/modelware/htdocs/MTengine_latest_build/BasicMTLc_latest.zip
