/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.core.MTLCore;

/**
 * @author edrezen
 *
 * This command analyzes the dependencies of a Folder.
 * From a given folder, it computes the collection of folders the given folder
 * depends on.
 * 
 */
public class DependenciesFullProjectCommand extends AbstractDependenciesCommand  
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private IProject project;

	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public IProject getProject() {
		return project;
	}
	public void setProject(IProject project) {
		this.project = project;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public DependenciesFullProjectCommand (IProject project)
	{
		setProject (project);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public Object preExecute() throws Exception 
	{
		MTLPlugin.instance().getModel(getProject()).setProject(getProject());
		MTLCore.loadMtlClasspath();
		return null;
	}

	
	/** */
	public Object mainExecute() throws Exception 
	{
		// we prepare a Collection (should contains IPath objects)
		java.util.Collection result = new java.util.Vector ();

		// we loop over the different folders of the current project.
		IPath[] srcPaths = MTLModel.srcFolders;
		for (int i=0; i<srcPaths.length; i++)
		{
			result.add (srcPaths[i]);
		}

		return result;
	}
}
