/*
 * Created on 28 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.core.MTLCore;

/**
 * @author edrezen
 *
 */
public class GetUserTLLCommand extends MTLCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private IProject project;
	public IProject getProject() { return project; }
	public void setProject(IProject project) { this.project = project; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public GetUserTLLCommand (IProject project)
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
		return super.preExecute();
	}

	
	/** */
	public Object mainExecute() throws Exception 
	{
		java.util.Collection result = new java.util.Vector ();

		// a little check does not hurt
		if (MTLModel.tllFolder.exists())
		{
			IResource[] members = MTLModel.tllFolder.members();
			for (int i=0; i<members.length; i++)
			{
				if (members[i] instanceof IFile)
				{
					IFile file = (IFile)members[i];
					if (file.getFileExtension().equals("tll"))
					{
						result.add (file.getLocation().toOSString());
					}
				}
			}
		}

		// we return the result
		return result;
	}
}
