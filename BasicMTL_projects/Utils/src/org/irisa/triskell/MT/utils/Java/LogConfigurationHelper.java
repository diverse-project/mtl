/* $Id: LogConfigurationHelper.java,v 1.1 2004-10-21 14:00:11 dvojtise Exp $
 * Created on May 22, 2003
 *
 * Authors : dvojtise
 */
package org.irisa.triskell.MT.utils.Java;

import java.io.File;

import org.apache.log4j.Logger;


/**
 * Some usefull methods relative configuration of log4j
 * @author dvojtise
 */
public class LogConfigurationHelper {

	static public boolean isConfigured=false;   
    /** reconfigure log4j using log4Jconfiguration file
     * Search strategy is to look for a log4j_configuration.xml file in the following folders:
     * 	1) folder defined by the system property named "Directories.RootPath"
     *  2) root folder that contains the fromClass class. usually, this folder is the one that contains the jar owning this class
     *  3) root folder that contains this class (Directories). usually, this folder is the one that contains the jar owning this Directories class
	 * 		this is the default behavior as this class is owned by the runtime.
	 * return an empty string if not found ...
	 */
    static public void reconfigureLog4j(String fromClass)
    {
    
    	String filePath = "";
    	File logConfigurationFile;
    	// try the Directories.RootPath and then the fromClass root 
    	logConfigurationFile  = new File(Directories.getRootPath(fromClass) + "/log4j_configuration.xml");
    	try{
    		if (logConfigurationFile.exists())
	    	{
	    		filePath = logConfigurationFile.getCanonicalPath();
	    	}
	    	else
	    	{
	    		// try the runtime root
	    		logConfigurationFile  = new File(Directories.getRootPath(Directories.class.getName()) + "/log4j_configuration.xml");
	    		if (logConfigurationFile.exists())
	    		{
	    			filePath = logConfigurationFile.getCanonicalPath();
	    		}
	    	} 
    	}
		catch (java.io.IOException e)
		{
			org.apache.log4j.BasicConfigurator.configure();
			Logger.getRootLogger().error(e);
		}
		org.apache.log4j.LogManager.resetConfiguration();
    	if( filePath =="")
    	{
    		org.apache.log4j.BasicConfigurator.configure();
    		Logger.getRootLogger().warn("not able to retrieve log4jconfiguration file; using default configuration (all messages on the console)");
    	}
    	else
    	{
    		org.apache.log4j.xml.DOMConfigurator.configure(filePath);
    		Logger.getRootLogger().info("log4j "+ (isConfigured ? "(re)" : "") + "configured with "+filePath);
    	}
		isConfigured = true;
    }


}
