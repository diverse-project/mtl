/*
 * Created on 18 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;

/**
 * @author edrezen
 *
 * Abstract class (for factorize code) that deals with MTL dependencies (between folders/tll)
 */
abstract public class AbstractDependenciesCommand extends MTLCommand
{
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	protected boolean hasToBeCompiled (IProject project, IPath folderPath) throws Exception
	{
		boolean result = false;
		
		// we try to retrieve from this project the IFolder object given the folder path
		IFolder theFolder = project.getFolder (folderPath.removeFirstSegments(1));
		
		// we loop over the members of the folder
		IResource[] members = theFolder.members();
		for (int i=0; i<members.length && result==false; i++)
		{
			if (MTLCommandExecutor.getTagResource(members[i]) == null)
			{
				result = true;
			}
		}
		
		MTLCommand.log().debug ("Folder '" + folderPath + "' has " + (result ? "" : "NOT") + " to be compiled...");
		
		// we return the result
		return result;
	}
	
}
