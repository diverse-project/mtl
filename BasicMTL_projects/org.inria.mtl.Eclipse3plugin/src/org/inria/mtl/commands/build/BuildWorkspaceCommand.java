/*
 * Created on 12 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLNature;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.inria.mtl.core.MTLCore;

/**
 * @author edrezen
 *
 * This class is a command to process the workspace.
 */
public class BuildWorkspaceCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute () throws Exception
	{
		Boolean result = new Boolean (true);
		Boolean midResult = null;

		MTLCommand.log().debug ("we are going to process the workbench...");

		// we retrieve the list of projects in the workspace 
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		// we loop over all projects of the workspace 
		for (int i=0; i<projects.length; i++)
		{
			//voir comment contrôler cette exécution
			if (projects[i].hasNature(MTLNature.NATURE_ID))
			{
				// we retrieve one project 
				IProject currentProject = projects[i].getProject();
				 
				MTLPlugin.instance().getModel(currentProject).setProject (currentProject);
				MTLCore.loadMtlClasspath();

				// we launch a command that do our job
				midResult = (Boolean) MTLCommandExecutor.buildProject (currentProject);
				
				// we build the result incrementally; if a single false mid result occurs, the final result will be false.
				result = new Boolean (result.booleanValue() && midResult.booleanValue());
			}
		}

		MTLCommand.log().debug ("the workbench has been processed...");

		return result;
	}
}
