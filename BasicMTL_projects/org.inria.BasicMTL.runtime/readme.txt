$Id: readme.txt,v 1.2 2004-08-16 11:40:17 dvojtise Exp $

This project aims at providing the BasicMTL runtime as an Eclipse plugin 
so it may be used by other plugins

it first copy revelant files from the BasicMTL2Java project
then it package it, jar file, + copy of used jars like MDR, log4J, etc

note: EMF and EMFDriver isn't included here, as this is the role 
 of org.inria.EMFDriver to to it.
 
 Note2: a lot of trouble finilizing this plugin, I still get a lot of classNotFound or undefClass exceptions
 when using this plugin from another one.
 This seems to be a configuration problem using the bundle loçading management
 If someone as an idea how to fix that ...