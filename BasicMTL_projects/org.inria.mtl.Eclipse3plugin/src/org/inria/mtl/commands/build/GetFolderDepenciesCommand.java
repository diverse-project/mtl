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
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.core.MTLCore;

/**
 * @author edrezen
 *
 * This command analyzes the dependencies of a Folder.
 * From a given folder, it computes the collection of folders the given folder
 * depends on.
 * 
 */
public class GetFolderDepenciesCommand extends MTLCommand  
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
	public GetFolderDepenciesCommand (IFolder folder)
	{
		setFolder (folder);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public Object mainExecute() throws Exception 
	{
		// we prepare a Collection (should contains IPath objects)
		java.util.Collection result = new java.util.Vector ();

		IProject project = getFolder().getProject();
		MTLPlugin.instance().getModel(project).setProject(project);
		MTLCore.loadMtlClasspath();
		
		IPath[] folders = MTLModel.srcFolders;
		// we loop over all the source folders (given by their IPath)
		for (int i=0; i<folders.length; i++)
		{
			result.add (folders[i]);
			if (folders[i].toString().equals (getFolder().getFullPath().toString()) )
			{
				break;
			}
		}
		
		// we return the result
		return result;
	}

	/** */
	public Object preExecute ()  throws Exception   { return null; } 
	public Object postExecute () throws Exception   { return null; } 

}


//On fait en sorte que le fichier soit obligatoirement compilé
//long oldGen = getProject().getModificationStamp();
//String newGen = ((oldGen==100) ? new Long(oldGen-1).toString() : new Long(oldGen+1).toString());
//getFolder().setPersistentProperty (new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
//MTLCommand.log().debug ("we are going to process the resource '" + getFolder() + "'  olGen=" + oldGen + "  newGen=" + newGen);


