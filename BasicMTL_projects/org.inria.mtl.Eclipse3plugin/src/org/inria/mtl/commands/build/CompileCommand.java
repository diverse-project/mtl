/*
 * Created on 12 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import java.util.Collection;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IPath;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

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

		// we tag the generated resources
//		MTLPlugin.factory().runSetTagResourceCommand (getLibraryJavaFolder(), true);
//		MTLPlugin.factory().runSetTagResourceCommand (getLibraryTllFile(),    true);

		return null;
	}
	
	
	/** @see org.inria.mtl.commands.MTLCommand#execute()
	 */
	public Object mainExecute() throws Exception 
	{
		// we initialize the message handler
		MSGHandler.reinit();

		// we create a new compiler
		BasicMtlCompiler.Compiler Comp = new BasicMtlCompiler.Compiler();
			
		// we may want some debugging messages for the compiler
		MTLCommand.log().debug ("CompileCommand : mtlSourceFolder     = " + getStringForMtlSourceFolder());
		MTLCommand.log().debug ("CompileCommand : libraryName         = " + getStringForLibraryName());
		MTLCommand.log().debug ("CompileCommand : javaTargetFolder    = " + getStringForJavaTargetFolder());
		MTLCommand.log().debug ("CompileCommand : tllTargetFolder     = " + getStringForTllTargetFolder());
		MTLCommand.log().debug ("CompileCommand : tllClasspathFolders = " + getStringForTllClasspathFolders());
		 
		// we launch the compiler
		Comp.compileFromDirectory (
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
			if (result==null)
			{
				result = checkPathEnd (path.toOSString());
			}
			else
			{
				result = result.concat(";");
				result = result.concat (path.toOSString());
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


	/** */
	private void quietCreateFolder (IFolder folder) throws Exception
	{
		if (! folder.exists())
		{
			folder.create (true,true,null);
		}
	}
	
}
