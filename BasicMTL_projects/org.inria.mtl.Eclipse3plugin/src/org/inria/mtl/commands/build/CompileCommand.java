/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.util.Collection;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;

/**
 * @author edrezen
 *
 * This command runs the MTL compiler.
 */

public class CompileCommand extends MTLCommand 
{
	private IFolder    mtlSourceFolder;
	private String     libraryName;
	private IFolder    javaTargetFolder;
	private IFolder    tllTargetFolder;
	private Collection tllClasspathFolders;
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public CompileCommand (
		IFolder mtlSourceFolder,
		String libraryName,
		IFolder javaTargetFolder,
		IFolder tllTargetFolder,
		java.util.Collection tllClasspathFolders
	)
	{
		this.mtlSourceFolder     = mtlSourceFolder;
		this.libraryName         = libraryName;
		this.javaTargetFolder    = javaTargetFolder;
		this.tllTargetFolder     = tllTargetFolder;
		this.tllClasspathFolders = tllClasspathFolders;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object preExecute() throws Exception 
	{
		// a little check on folders for generated products
		quietCreateFolder (javaTargetFolder);
		quietCreateFolder (tllTargetFolder);
		quietCreateFolder (getLibraryJavaFolder());

		MTLCommandExecutor.removeContents (getLibraryJavaFolder(), null);
		MTLCommandExecutor.removeResource (getLibraryTllFile(),    null);

		return null;
	}

	
	/** */
	public Object postExecute() throws Exception 
	{
		// we refresh the generated resources
		MTLCommandExecutor.refreshResource (getLibraryJavaFolder(), null);
		MTLCommandExecutor.refreshResource (getLibraryTllFile(),    null);

		// we tag the sources
		MTLCommandExecutor.setTagResource (mtlSourceFolder, true);

		return null;
	}
	
	
	/** @see org.inria.mtl.commands.MTLCommand#execute()
	 */
	public Object mainExecute() throws Exception 
	{
		// we may want some debugging messages for the compiler
		MTLCommand.log().debug ("CompileCommand : mtlSourceFolder     = " + getStringForMtlSourceFolder());
		MTLCommand.log().debug ("CompileCommand : libraryName         = " + getStringForLibraryName());
		MTLCommand.log().debug ("CompileCommand : javaTargetFolder    = " + getStringForJavaTargetFolder());
		MTLCommand.log().debug ("CompileCommand : tllTargetFolder     = " + getStringForTllTargetFolder());
		MTLCommand.log().debug ("CompileCommand : tllClasspathFolders = " + getStringForTllClasspathFolders());
		 
		// we launch the compiler
		BasicMtlCompiler.Compiler.instance().compileFromDirectory (
            /* the MTL source folder */   getStringForMtlSourceFolder(),
			/* the library name */        getStringForLibraryName(),
			/* the TLL target folder */   getStringForTllTargetFolder(),
			/* the TLL classpath */       getStringForTllClasspathFolders(),
			/* the java target folder */  getStringForJavaTargetFolder()
		);

		return null;
	}

	/** */
	private String getStringForMtlSourceFolder ()
	{
		return checkPathEnd (mtlSourceFolder.getLocation().toString());
	}
	
	/** */
	private String getStringForLibraryName ()
	{
		return libraryName;
	}
	
	/** */
	private String getStringForJavaTargetFolder ()
	{
		return checkPathEnd (javaTargetFolder.getFolder(libraryName).getLocation().toString());
	}
	
	/** */
	private String getStringForTllTargetFolder ()
	{
		return checkPathEnd (tllTargetFolder.getLocation().toString());
	}
	
	/** */
	private String getStringForTllClasspathFolders ()
	{
		String result = null;
		java.util.Iterator it = tllClasspathFolders.iterator();
		while (it.hasNext())
		{
			IPath path = (IPath) it.next();
			String s = checkPathEnd (path.toOSString());
			
			if (result==null)
			{
				result = s;
			}
			else
			{
				result = result.concat(";");
				result = result.concat (s);
			}
		}
		
		return result; 
	}
	
	
	/** */
	private String checkPathEnd (String path)
	{
		if (path.charAt(path.length()-1) == MTLPlugin.getSeparator())
		{
			return path;
		}
		return path + MTLPlugin.getSeparator(); 
	}
	
	
	/** Returns the folder of the generated java code for the current library. */
	private IFolder getLibraryJavaFolder ()
	{
		return javaTargetFolder.getFolder(libraryName);
	}

	/** Returns the file of the generated TLL for the current library. */
	private IFile getLibraryTllFile ()
	{
		return tllTargetFolder.getFile (libraryName + ".tll");
	}


	/** Create a folder if needed. It creates the hierarchy of folders if outermost folders do not exists. */
	private void quietCreateFolder (IFolder folder) throws CoreException 
	{
		IProject project = folder.getProject();
		IPath path = folder.getFullPath().removeFirstSegments(1);
		for (int i=path.segmentCount()-1; i>=0; i--)
		{
			IPath loopPath = path.removeLastSegments(i);
			IFolder loopFolder = project.getFolder (loopPath);
			if (! loopFolder.exists())
			{
				loopFolder.create (true,true,null);
			}
		}
	}
}
