/*
 * Created on 12 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.build;

import java.util.Collection;

import org.eclipse.core.resources.IFolder;
import org.inria.mtl.commands.MTLCommandExecutor;

/**
 * @author edrezen
 *
 * This class is a command to process a folder.
 */
public class BuildFolderCommand extends AbstractBuildCommand 
{
	/** */
	public BuildFolderCommand (IFolder theFolder) throws Exception
	{
		super ((Collection) MTLCommandExecutor.getFolderDepencies(theFolder));
	}
}
