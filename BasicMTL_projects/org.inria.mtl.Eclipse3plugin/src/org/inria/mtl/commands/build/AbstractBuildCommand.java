/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.util.Collection;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;

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
				getTllPaths()
			);

			// we may want to notify observers that we have finished the compilation
			this.notifyObservers (null);
		}

		return Boolean.TRUE;
	}
	
	
	/** This method builds a collection of IPath objects that refer folders to be used by the 
	 * compiler in the TLL classpath.
	 * */
	private java.util.Collection getTllPaths ()
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
