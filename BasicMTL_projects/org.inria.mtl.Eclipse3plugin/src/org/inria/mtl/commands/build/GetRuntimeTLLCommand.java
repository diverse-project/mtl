/*
 * Created on 28 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.io.File;

import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 * This class returns a Collection of java.io.File objects that contains the runtime TLLs.
 * 
 */
public class GetRuntimeTLLCommand extends MTLCommand
{
	/** */
	public Object mainExecute() throws Exception 
	{
		java.util.Collection result = new java.util.Vector ();
		char s = MTLPlugin.getSeparator();

		// we retrieve the name of the folder that contains the runtime TLLs.
		String tllFolderName = MTLPlugin.getDefault().getLocation() + s + "MTL" + s + "bin" + s + "Compil" + s + "TLL";

		// we get a IFolder from this name
		File folder = new File (tllFolderName);
		if (folder.isDirectory())
		{
			File[] files = folder.listFiles();
			for (int i=0; i<files.length; i++)
			{
				if (files[i].getName().endsWith(".tll"))
				{
					result.add (files[i].getAbsolutePath());
				}
			}
		}
		
		// we return the result
		return result;
	}

}
