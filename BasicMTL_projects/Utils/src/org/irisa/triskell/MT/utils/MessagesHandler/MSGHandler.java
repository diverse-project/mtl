/* $Id: MSGHandler.java,v 1.10 2004-10-21 14:05:19 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.utils.MessagesHandler;

//import java.util.Enumeration;
import java.util.Vector;

//import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.irisa.triskell.MT.utils.Java.Directories;
import org.irisa.triskell.MT.utils.Java.LogConfigurationHelper;

/**
 *This class manages all compiler messages and ask the compiler for informations on processed file,line number and so on
 *It calls log4j to export the messages in a fixed format.
 */
public class MSGHandler {
	
	public static String processedMtlFile = null;
	public static int processedMtlLineNumber = 0;

	public static Vector allMessages=null;
	
/*	public static final org.apache.log4j.Logger log = Logger.getLogger("MSGHandler");

	public static org.apache.log4j.Logger getLog () {
			return MSGHandler.log;
	}*/
	
	
	/**
	 * initialize the log4j system and create an empty message vector 
	 */
	public static Logger init() {
		String filePath="";
		Logger logger=null;
		if (allMessages==null) {
			allMessages=new Vector();
		
		
			LogConfigurationHelper.reconfigureLog4j(MSGHandler.class.getName());					
			logger=Logger.getLogger("MSGHandler");
			if (logger.getAppender("MSGHandlerAppender")==null)
				logger.addAppender(new MSGHandlerAppender());		
		}
		else logger=Logger.getLogger("MSGHandler");
		return logger;
	}
	
	/**
	 * empty the message vector
	 */
	public static void reinit() {
			allMessages=new Vector();
			
	}
	
/*	public static void info(Class origin,int originLineNumber,String msg) {
		if (allMessages == null) init();
		log.info(msg);
		allMessages.add(new CompilerMessage("info",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}

	public static void warn(Class origin,int originLineNumber,String msg) {
		if (allMessages == null) init();
		log.warn(msg);
		allMessages.add(new CompilerMessage("warn",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}
	public static void debug(Class origin,int originLineNumber,String msg) {
		if (allMessages == null) init();
		log.debug(msg);
		allMessages.add(new CompilerMessage("debug",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}
	
	public static void error(Class origin,int originLineNumber,String msg) {
		if (allMessages == null) init();
		log.error(msg);
		allMessages.add(new CompilerMessage("error",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));		
	}

	public static void fatal(Class origin,int originLineNumber,String msg) throws CompilerException {
		if (allMessages == null)
		init();
		log.error(msg);
		allMessages.add(new CompilerMessage("fatal",msg,processedMtlFile,processedMtlLineNumber,origin,originLineNumber));
		throw new CompilerException("Basic MTL Fatal Error.");
	}*/
}
