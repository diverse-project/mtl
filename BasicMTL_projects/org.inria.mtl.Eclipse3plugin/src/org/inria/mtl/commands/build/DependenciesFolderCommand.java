/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;

/**
 * @author edrezen
 *
 * This command analyzes the dependencies of a Folder.
 * From a given folder, it computes the collection of folders that need to be
 * compiled in order the given folder also compiles.
 * 
 */
public class DependenciesFolderCommand extends AbstractDependenciesCommand  
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private IFolder folder;

	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public IFolder getFolder() {
		return folder;
	}
	public void setFolder(IFolder folder) {
		this.folder = folder;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public DependenciesFolderCommand (IFolder folder)
	{
		setFolder (folder);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public Object preExecute() throws Exception 
	{
		IProject project = getFolder().getProject();
		MTLPlugin.instance().getModel(project).setProject(project);
		MTLCore.loadMtlClasspath();
		return null;
	}

	
	/** */
	public Object mainExecute() throws Exception 
	{
		// we prepare a Collection (should contains IPath objects)
		java.util.Collection result = new java.util.Vector ();

		IPath[] folders = MTLModel.srcFolders;
		// we loop over all the source folders (given by their IPath)
		for (int i=0; i<folders.length; i++)
		{
			// the loop item folder may need to be compiled
			if (hasToBeCompiled (getFolder().getProject(), folders[i]))
			{
				result.add (folders[i]);
			}
			
			if (folders[i].toString().equals (getFolder().getFullPath().toString()) )
			{
				break;
			}
		}
		
		// we return the result
		return result;
	}
}
