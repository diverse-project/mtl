/* $Id: MSGHandler.java,v 1.2 2004-06-09 09:35:08 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.utils.MessagesHandler;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.utils.Java.Directories;

/**
 *This class manages all compiler messages and ask the compiler for informations on processed file,line number and so on
 *It calls log4j to export the messages in a fixed format.
 */
public class MSGHandler {
	
	public static String processedMtlFile = null;
	public static int processedMtlLineNumber = 0;

	public static Vector allMessages=null;
	
	public static final org.apache.log4j.Logger log = Logger.getLogger("MSGHandler");

	public static org.apache.log4j.Logger getLog () {
			return MSGHandler.log;
	}
	
	public static void init() {
		try {
			String filePath = new java.io.File(Directories.getRootPath(MSGHandler.class.getName()) + "/log4j_configuration.xml").getCanonicalPath();
			LogManager.resetConfiguration();
			DOMConfigurator.configure(filePath); }
		catch(java.io.IOException e) {
							System.err.println("Can't state log4j in MSGHandler"); }
		allMessages=new Vector();
	}
	
	public static void info(Class origin,int originLineNumber,String msg) {
		log.info(msg);
		allMessages.add(new CompilerMessage("info",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}

	public static void warn(Class origin,int originLineNumber,String msg) {
		log.warn(msg);
		allMessages.add(new CompilerMessage("warn",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}
	public static void debug(Class origin,int originLineNumber,String msg) {
		log.debug(msg);
		allMessages.add(new CompilerMessage("debug",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}
	
	public static void error(Class origin,int originLineNumber,String msg) {
		log.error(msg);
		allMessages.add(new CompilerMessage("error",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}
}