/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.util.Collection;
import java.util.Observer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
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
		
		// we manage the classifier observers
		for (java.util.Iterator it=observers.iterator(); it.hasNext(); )
		{
			this.addObserver ((Observer) it.next());
		}
	}
	

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object preExecute() throws Exception 
	{
		// we may have to do nothing
		if (getDependencies().size()==0)  {return Boolean.TRUE; }
		
		// some clean up before starting
		MTLConsole.cleanConsole();
		
		// we initialize the message handler
		MSGHandler.init();

		// we clean the previous markers
		for (java.util.Iterator it=getDependencies().iterator(); it.hasNext(); )
		{
			IFolder loopFolder = (IFolder) MTLPlugin.getWorkspace().getRoot().findMember ((IPath)it.next());
			loopFolder.deleteMarkers (IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			loopFolder.deleteMarkers (IMarker.TASK,    true, IResource.DEPTH_INFINITE);
		}
		MTLModel.getProject().deleteMarkers (IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		MTLModel.getProject().deleteMarkers (IMarker.TASK,    true, IResource.DEPTH_INFINITE);
		
		return super.preExecute();
	}
	
	
	/** */
	public Object mainExecute () throws Exception
	{
		// we may have to do nothing
		if (getDependencies().size()==0)  {return Boolean.TRUE; }

		try {
			// we loop over all the source folders that need to be compiled
			java.util.Iterator it = getDependencies().iterator();
			while (it.hasNext())
			{
				// we get the item loop IPath object and remove the project segment
				IPath loopPath = (IPath)it.next();
				
				// we retrieve the corresponding IFolder object			
				IFolder loopFolder = (IFolder) MTLPlugin.getWorkspace().getRoot().findMember (loopPath);
	
				// we retrieve the corresponding project
				IProject project = loopFolder.getProject();
				
				// we may want to notify observers that we are about to start the compilation
				this.notifyObservers (new Object[] {"before", loopFolder});
	
				// we launch the compilation
				Boolean result = (Boolean) MTLCommandExecutor.compile (
					loopFolder, 
					loopFolder.getName(), 
					MTLModel.srcJavaFolder, 
					MTLModel.tllFolder, 
					(Collection) MTLCommandExecutor.getTllPaths(project)
				);
	
				// we may want to notify observers that we have finished the compilation
				this.notifyObservers (new Object[] {"after", loopFolder.getName(), MTLModel.tllFolder});
			}
		}
		catch (Exception e)
		{
			// the compiler may have launched an exception because of compilation errors.
			// in that case, we must proceed the post execution.
			postExecute();
		}

		return Boolean.TRUE;
	}
	
	
	/** */
	public Object postExecute() throws Exception 
	{
		// we may have to do nothing
		if (getDependencies().size()==0)  {return Boolean.TRUE; }

		return super.postExecute();
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CLASSIFIER OBSERVERS MANAGEMENT
	////////////////////////////////////////////////////////////////////////////////
	static private java.util.Collection observers = new java.util.HashSet();
	public static void addClassifierObserver    (Observer observer) { observers.add (observer);   }
	public static void removeClassifierObserver (Observer observer) { observers.remove(observer); }

}
