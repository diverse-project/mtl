$Id: readme.txt,v 1.4 2004-10-25 12:27:16 dvojtise Exp $
This project provides
- the build process for a complete BasicMTL compiler (integration of the different parts and distribution)
- test of the compiler (regression test)
- ant integration : the compiler can be used as an ant task
- the scripts used to compile the compiler every night

In odrer to run, the user must choose and eventually adapt a build.properties files from build.properties.samples.
and copy it in the root of this project. (the differents build files rely on it.

This project goal is to provide a standalone version of BasicMTL compiler.
If you look for a version to be run and used as Eclipse plugin, please refer to the feature and 
site projects which provide Eclipse style deployment.

History:
$Log: not supported by cvs2svn $
Revision 1.3  2004/03/15 14:28:43  dvojtise
separates the location of the used plugins in a property file so the end user can adapt it to his configuration.

