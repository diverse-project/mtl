/*
 * Created on 13 oct. 2004
 *
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandDebug;
import org.inria.mtl.commands.MTLCommandWithProgressMonitor;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;

/**
 * @author edrezen
 *
 * Abstract Factory (see [GOF] for creating build commands
 */
public class BuildCommandFactory 
{
	////////////////////////////////////////////////////////////////////////////////
	// BUILD COMMANDS
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception */
	public MTLCommand createBuildFolderCommand (IFolder theFolder) throws Exception
	{
		return new MTLCommandDebug (new BuildFolderCommand (theFolder));
	}

	/**
	 * @throws Exception */
	public MTLCommand createBuildProjectCommand (IProject theProject) throws Exception
	{
		BuildProjectCommand delegate = new BuildProjectCommand (theProject);
		return new MTLCommandWithProgressMonitor (delegate, theProject.getName(), delegate.getDependencies().size());
	}

	/**
	 * @throws Exception */
	public MTLCommand createBuildFullProjectCommand (IProject theProject) throws Exception
	{
		BuildFullProjectCommand delegate = new BuildFullProjectCommand (theProject);
		return new MTLCommandWithProgressMonitor (delegate, theProject.getName(), delegate.getDependencies().size());
	}

	
	/** */
	public MTLCommand createGetFolderDepenciesCommand (IFolder folder)
	{
		return new DependenciesFolderCommand (folder);
	}

	/** */
	public MTLCommand createGetProjectDepenciesCommand (IProject project)
	{
		return new DependenciesProjectCommand (project);
	}

	/** */
	public MTLCommand createGetFullProjectDepenciesCommand (IProject project)
	{
		return new DependenciesFullProjectCommand (project);
	}

	
	/** */
	public MTLCommand createCompileCommand (
		IFolder mtlSourceFolder,
		String libraryName,
		IFolder javaTargetFolder,
		IFolder tllTargetFolder,
		java.util.Collection tllClasspathFolders
	)
	{
		return 	new CompileCommand (mtlSourceFolder, libraryName, javaTargetFolder, tllTargetFolder, tllClasspathFolders);
	}

	/** */
	public MTLCommand createGetFileFromCompilerMessageCommand (CompilerMessage message)
	{
		return new GetFileFromCompilerMessageCommand (message);
	}
	
	/** */
	public MTLCommand createGetTllPathsCommand ()
	{
		return new GetTllPathsCommand ();
	}
}
