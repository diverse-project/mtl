/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.builders.MTLModel;

/**
 * @author edrezen
 *
 * This command analyzes the dependencies of a Folder.
 * From a given folder, it computes the collection of folders the given folder
 * depends on.
 * 
 */
public class DependenciesProjectCommand extends DependenciesFullProjectCommand  
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public DependenciesProjectCommand (IProject project)
	{
		super (project);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public Object mainExecute() throws Exception 
	{
		// we prepare a Collection (should contains IPath objects)
		java.util.Collection result = new java.util.Vector ();

		// we loop over the different folders of the current project.
		IPath[] srcPaths = MTLModel.srcFolders;
		for (int i=0; i<srcPaths.length; i++)
		{
			if (hasToBeCompiled(getProject(),srcPaths[i]))
			{
				result.add (srcPaths[i]);
			}
		}

		return result;
	}
}
