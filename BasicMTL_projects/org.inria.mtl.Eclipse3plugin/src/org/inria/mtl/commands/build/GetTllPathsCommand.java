/*
 * Created on 21 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.runtime.IPath;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 * This method builds a collection of IPath objects that refer folders to be used by the 
 * compiler in the TLL classpath.
 */
public class GetTllPathsCommand extends MTLCommand 
{
	/** */
	public Object mainExecute() throws Exception 
	{
		java.util.Collection result = new java.util.Vector ();  // collection of IPath objects

		// we build a Collection of IPath objects that represents TLL files to be used by the compiler
		for (int i=0; i<MTLModel.libFolders.length; i++)
		{
			IPath path = MTLModel.libFolders[i];
			String ext = path.getFileExtension();
			
			if (ext!=null && ext.equals("tll"))
			{
				path = path.removeLastSegments(1);
			}

			if (! result.contains (path))
			{
				result.add (path);
			}
		}

		return result;
	}
}
