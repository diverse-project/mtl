/*
 * Created on 13 oct. 2004
 */
package org.inria.mtl.commands;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.inria.mtl.commands.build.BuildCommandFactory;
import org.inria.mtl.commands.markers.MarkersCommandFactory;
import org.inria.mtl.commands.resources.ResourcesCommandFactory;
import org.inria.mtl.commands.tag.TagCommandFactory;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;

/**
 * @author edrezen
 *
 * This class is a facade for executing command coming from different commands factories
 */

public class MTLCommandExecutor 
{
	static private BuildCommandFactory     build    = new BuildCommandFactory ();
	static private MarkersCommandFactory   markers  = new MarkersCommandFactory (); 
	static private ResourcesCommandFactory resource = new ResourcesCommandFactory (); 
	static private TagCommandFactory       tag      = new TagCommandFactory (); 
	
	////////////////////////////////////////////////////////////////////////////////
	// BUILD COMMANDS
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception */
	static public Object buildFolder (IFolder theFolder) throws Exception
	{
		return build.createBuildFolderCommand (theFolder).execute ();
	}

	/**
	 * @throws Exception */
	static public Object buildProject (IProject theProject) throws Exception
	{
		return build.createBuildProjectCommand (theProject).execute ();
	}

	/**
	 * @throws Exception */
	static public Object buildFullProject (IProject theProject) throws Exception
	{
		return build.createBuildFullProjectCommand (theProject).execute ();
	}

	/**
	 * @throws Exception */
	static public Object getFolderDepencies (IFolder folder) throws Exception
	{
		return build.createGetFolderDepenciesCommand (folder).execute ();
	}
	
	/**
	 * @throws Exception */
	static public Object getProjectDepencies (IProject project) throws Exception
	{
		return build.createGetProjectDepenciesCommand (project).execute ();
	}

	/**
	 * @throws Exception */
	static public Object getFullProjectDepencies (IProject project) throws Exception
	{
		return build.createGetFullProjectDepenciesCommand (project).execute ();
	}

	/**
	 * @throws Exception */
	static public Object compile (
		IFolder mtlSourceFolder,
		String libraryName,
		IFolder javaTargetFolder,
		IFolder tllTargetFolder,
		java.util.Collection tllClasspathFolders
	) throws Exception
	{
		return build.createCompileCommand (mtlSourceFolder, libraryName, javaTargetFolder, tllTargetFolder, tllClasspathFolders).execute();
	}

	/**
	 * @throws Exception */
	static public Object getFileFromCompilerMessage (CompilerMessage message) throws Exception
	{
		return build.createGetFileFromCompilerMessageCommand (message).execute();
	}


	////////////////////////////////////////////////////////////////////////////////
	// MARKERS COMMANDS
	////////////////////////////////////////////////////////////////////////////////
	static public Object createMarkers (java.util.Vector vector) throws Exception
	{
		return markers.createCreateMarkersCommand (vector).execute();
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// RESOURCES COMMANDS
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @throws Exception */
	static public Object removeResource (IResource res, String tagName) throws Exception
	{
		return resource.createRemoveResourceCommand (res, tagName).execute (); 
	}

	/**
	 * @throws Exception */
	static public Object removeContents (IResource res, String tagName) throws Exception
	{
		return resource.createRemoveContentsCommand (res, tagName).execute (); 
	}
	
	/**
	 * @throws Exception */
	static public Object setTagResource (IResource res, String tagName, boolean isSet) throws Exception
	{ 
		return resource.createSetTagResourceCommand (res, isSet).execute ();
	}

	/**
	 * @throws Exception */
	static public Object refreshResource (IResource res, String tagName) throws Exception
	{
		return resource.createRefreshResourceCommand (res, tagName).execute ();
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// TAG COMMANDS
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @throws Exception */
	static public Object setTagResource (IResource resource, boolean setUnset) throws Exception
	{
		return 	tag.createSetTagResourceCommand (resource, setUnset).execute ();
	}

	/**
	 * @throws Exception */
	static public Object getTagResource (IResource resource) throws Exception
	{
		return 	tag.createGetTagResourceCommand (resource).execute ();
	}
	
}
