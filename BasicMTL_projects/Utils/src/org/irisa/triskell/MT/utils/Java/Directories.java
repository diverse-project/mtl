/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

import java.io.File;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Directories {

	public static File getUserPath () {
		File ret = new File(System.getProperty("user.home"));
		if (! ret.exists())
			ret.mkdirs();
		return ret;
	}

    public static File getJar (String fromClass) {
        String thisClassName = ClassLoader.getSystemResource(AWK.replace(fromClass,
                ".", "/") + ".class").getFile();
        if (thisClassName.startsWith("file:")) {
            int index = thisClassName.indexOf("!");
            if (index < 0)      // Should not happen, we are in a jar
                thisClassName = thisClassName.substring(5);                     // Eliminate file:
            else
                thisClassName = thisClassName.substring(5, index);              // Eliminate file: and the class part
            try {
                thisClassName = java.net.URLDecoder.decode(thisClassName);
            } catch (Exception ex) {
            }
            return new File(thisClassName);
        }
        else {
            return null;
        }
    }

    /**Get the path where the program is installed, class or jar
     * By fixing the system property Directories.RootPath to the value you want, you can force this method to respond it.
     */
    public static String getRootPath (String fromClass) {
    	String ret = System.getProperty("Directories.RootPath");
    	if (ret != null && ret.length() > 0)
    		return new File(ret).getAbsolutePath();
        String thisClassName = ClassLoader.getSystemResource(AWK.replace(fromClass,
                ".", "/") + ".class").getFile();
        if (thisClassName.startsWith("file:")) {
            int index = thisClassName.indexOf("!");
            if (index < 0)      // Should not happen, we are in a jar
                thisClassName = thisClassName.substring(5);                     // Eliminate file:
            else
                thisClassName = thisClassName.substring(5, index);              // Eliminate file: and the class part
            try {
                thisClassName = java.net.URLDecoder.decode(thisClassName);
            } catch (Exception ex) {
            }
        }
        else {
            String path = new File(thisClassName).getAbsolutePath();
            int length = AWK.numberOfFieldOf(fromClass,
                    ".");
            for (int i = 1; i < length; ++i)
                path = AWK.eliminateLastFieldOf(path, File.separator);
            thisClassName = path;
        }
        File thisFile = new File(thisClassName);
        return thisFile.getParent();
    }
}
