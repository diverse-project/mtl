/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.util.Collection;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.inria.mtl.views.MTLConsole;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

/**
 * @author edrezen
 *
 * This class is a command to process a folder.
 */
abstract public class AbstractBuildCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private Collection dependencies;


	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public Collection getDependencies() {
		return dependencies;
	}
	public void setDependencies(Collection dependencies) {
		this.dependencies = dependencies;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public AbstractBuildCommand (Collection dependencies)
	{
		setDependencies (dependencies);
	}
	

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object preExecute() throws Exception 
	{
		// some clean up before starting
		MTLConsole.cleanConsole();
		
		// we initialize the message handler
		MSGHandler.reinit();

		// we clean the previous markers
		for (java.util.Iterator it=getDependencies().iterator(); it.hasNext(); )
		{
			IFolder loopFolder = (IFolder) MTLPlugin.getWorkspace().getRoot().findMember ((IPath)it.next());
			loopFolder.deleteMarkers (IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			loopFolder.deleteMarkers (IMarker.TASK,    true, IResource.DEPTH_INFINITE);
		}
		
		return super.preExecute();
	}
	
	
	/** */
	public Object mainExecute () throws Exception
	{
		// we loop over all the source folders that need to be compiled
		java.util.Iterator it = getDependencies().iterator();
		while (it.hasNext())
		{
			// we get the item loop IPath object and remove the project segment
			IPath loopPath = (IPath)it.next();
			
			// we retrieve the corresponding IFolder object			
			IFolder loopFolder = (IFolder) MTLPlugin.getWorkspace().getRoot().findMember (loopPath);

			// we may want to notify observers that we are about to start the compilation
			this.notifyObservers (loopFolder);

			// we launch the compilation
			Boolean result = (Boolean) MTLCommandExecutor.compile (
				loopFolder, 
				loopFolder.getName(), 
				MTLModel.srcJavaFolder, 
				MTLModel.tllFolder, 
				(Collection) MTLCommandExecutor.getTllPaths()
			);

			// we may want to notify observers that we have finished the compilation
			this.notifyObservers (null);
		}

		return Boolean.TRUE;
	}
	
	
	/** */
	public Object postExecute() throws Exception 
	{
		// some post processings...
		MTLCommandExecutor.createMarkers (MSGHandler.allMessages);
		
		return super.postExecute();
	}
}
