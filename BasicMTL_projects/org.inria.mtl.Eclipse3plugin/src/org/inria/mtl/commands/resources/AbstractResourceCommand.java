/*
 * Created on 13 oct. 2004
 */
package org.inria.mtl.commands.resources;

import org.eclipse.core.resources.*;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 */
abstract public class AbstractResourceCommand extends MTLCommand
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private String tagName;
	private IResource resource;


	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public IResource getResource() {
		return resource;
	}
	public void setResource(IResource resource) {
		this.resource = resource;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public AbstractResourceCommand (IResource resource)
	{
		setResource (resource);
	}


	////////////////////////////////////////////////////////////////////////////////
	// FINAL METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** Template Method (see [GOF])
	 * It has specific behaviors according to the kind of the resource.
	 * There is a Template Method primitive to be redefined for each kind.
	 */
	final private Object processResource (IResource res) throws Exception
	{
		Object result = null;
		
		////////////////////////////////////////
		// the resource is a IFile
		////////////////////////////////////////
		if (res instanceof IFile)
		{
			IFile file = (IFile)res;
			result = processFile (file);
		}
		
		////////////////////////////////////////
		// the resource is a IFolder
		////////////////////////////////////////
		else if (res instanceof IFolder)
		{
			IFolder folder = (IFolder)res;
			IResource[] contents = folder.members();
			for (int i=0; i<contents.length; i++)
			{
				processResource (contents[i]);
			}
			result = processFolder (folder);
		}
		
		////////////////////////////////////////
		// the resource is a IProject
		////////////////////////////////////////
		else if (res instanceof IProject)
		{
			IProject project = (IProject)res;
			IResource[] contents = project.members();
			for (int i=0; i<contents.length; i++)
			{
				processResource (contents[i]);
			}
			result = processProject (project);
		}

		return result;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS WITH DEFAULT BEHAVIOR
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute() throws Exception  {	return processResource (getResource()); }

	/** */
	public Object processFile    (IFile file)       throws Exception  { return common(file);    }
	public Object processFolder  (IFolder folder)   throws Exception  { return common(folder);  }
	public Object processProject (IProject project) throws Exception  { return common(project); }

	public Object common (IResource res) throws Exception  { return null; }
	
}
