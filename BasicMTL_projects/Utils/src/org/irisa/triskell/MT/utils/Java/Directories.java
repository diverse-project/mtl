/* $Id: Directories.java,v 1.5 2004-06-29 13:12:08 edrezen Exp $
 * Created on May 22, 2003
 *
 * Authors : ffondemen
 */
package org.irisa.triskell.MT.utils.Java;

import java.io.File;

/**
 * Some usefull methods relative to directories
 * @author ffondeme
 */
public class Directories {

	/**
	 * Finds the user directory (from the system property/environment)
	 * @return File
	 */
	public static File getUserPath () {
		File ret = new File(System.getProperty("user.home"));
		if (! ret.exists())
			ret.mkdirs();
		return ret;
	}

    /**
     * Returns the jar file which contains that class 
	 * @param fromClass
	 * @return File
	 */
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
                thisClassName = java.net.URLDecoder.decode(thisClassName,"ISO-8859-1");
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
                thisClassName = java.net.URLDecoder.decode(thisClassName,"ISO-8859-1");
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
    

	/** This method returns the list of filenames in the provided path name. This is done recursivly.
	 * The returned strings are relative to the initial dir
	 * The initial directory must already have a separator (for example '/')
	 */
	static public String[] getFilesFromDirectory (String dir)
	{
		java.util.Vector vec = getFilesAsVectorFromDirectory (dir);
		String result[] = new String[vec.size()];
		for (int i=0; i<result.length; i++)
		{
			result[i] = (String)vec.get(i);
			result[i] = result[i].substring (dir.length()); 
		}
		return result;
	}

	/** This method returns the list of filenames in the provided path name. This is done recursivly.
	 */
	static public java.util.Vector getFilesAsVectorFromDirectory (String dir)
	{
		java.util.Vector result = new java.util.Vector ();
		
		// we get the list of resouces in the provided directory
		java.io.File file = new java.io.File (dir);
		if (file.isDirectory())
		{
			java.io.File filesList[] = file.listFiles();
			for (int i=0; i<filesList.length; i++)
			{
				result.addAll (getFilesAsVectorFromDirectory(filesList[i].getAbsolutePath() ));			
			}
		}
		else if (file.isFile())
		{
			result.add (file.getAbsolutePath());
		}
		return result;
	}
}
