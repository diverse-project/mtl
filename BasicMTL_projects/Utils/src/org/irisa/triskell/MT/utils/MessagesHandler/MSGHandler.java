/* $Id: MSGHandler.java,v 1.1 2004-06-04 13:01:56 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.utils.MessagesHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.irisa.triskell.MT.utils.Java.Directories;

/**
 *This class manages all compiler messages and ask the compiler for informations on processed file,line number and so on
 *It calls log4j to export the messages in a fixed format.
 */
public class MSGHandler {

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
	}
	
	public static void info(String msg) {
		
	}

	public static void warn(String msg) {
		
	}
	public static void debug(String msg) {
		
	}
	
	public static void error(String msg) {
		
	}
}
