 /* $Id: generateAntfilesAction.java,v 1.3 2005-02-24 14:05:14 dvojtise Exp $
 * Project  : org.inria.mtl.Eclipse3plugin
 * Filename : $File:$
 * License  : LGPL
 * Authors  : zdrey@irisa.fr
 * 
 * Creation date     : Jan 19, 2005
 * Modification date : $Date: 2005-02-24 14:05:14 $
 * 
 * Description       : 
 * This code creates a Button that is associated to this action :
 * the generation, for the currently selected MTL Project, of a set of *_build.xml files, from the informations
 * given in .mtlclasspath and .classpath of the MTL Project, so that the user can then compile and run a MTL Project without
 * bothering at writing manually systematic ant files, that
 * he created with the Eclipse MTL plugin, outside of Eclipse.
 */
package org.inria.mtl.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.inria.mtl.MTLPlugin;

/**
 * @author zdrey
 * 
 * Description : action associate to the button: generate ant files correspnding to the MTL project build configuration 
 */
public class generateAntfilesAction implements IWorkbenchWindowActionDelegate {

	private ResourceBundle resourceBundle;
	private ResourcesPlugin resourcesplugin;
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private ISelection selection=null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;

	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	  *  Gets the baseURL attribute of the MTLPlugin directory
	  *  (copied from MTLPlugin main class), and add a path chunk to it.
	  * @param a path chunk, that is a path relative to the current plugin directory
	  * @return    The platform-dependent absolute path of the given "path chunk"
	  * Note: a patch is used to remove the leading / on windows platform  (otherwise we get something like:  /C:/mydir/blabla
	  */
	
	public String getPathFromRelative(String pathChunk)
	{		
		String path = "";
		URL cmd_url;
		try
		{			
			cmd_url = new URL(
					org.inria.mtl.MTLPlugin.getBaseURL(),
					pathChunk);
			Path nP = new Path(Platform.resolve(cmd_url).getPath());

		    path = pathToOSString(nP.toOSString());
		    
		}
		catch (Exception e)
		{
			// should not append
			System.err.print("Could not find "+ pathChunk +" file");
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * // sometime on windows system, the path still start with / even if has C:  ex: /C:/mydir
	 *  PATCH: we really want an OSString ...
	 * @param path
	 * @return
	 */
	String pathToOSString(String path)
	{
		String osPath;
		Path nP = new Path(path);

		osPath = nP.toOSString();
		if (nP.getDevice().endsWith(":") && nP.getDevice().startsWith("/"))
		{	// windows system but start with / ??? PATCH: we really want an OSString ...
			osPath = osPath.substring(1);
		}
		return osPath;
	}
	public String getDefaultBasicMTLcDir()
	{
		return pathToOSString(MTLPlugin.getDefault().getLocation()) + "MTL" + System.getProperty("file.separator") + "bin";
	}
	
	/**
	 * Run the python Antimtl.py on the selected project, or, if user specified either many or no
	 * project(s), ask him to choose one through an inputDialog.
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) 
	{
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
		String project_path = "";
		String line = ""; String str = ""; String errstr = "";
		Shell shell = window.getShell();
		
		// A property that is set in MTLPlugin.java when log4j is not configured...(getLogger method) I need this property.
		String rootpath = System.getProperty("Directories.RootPath");
		String sep 		= System.getProperty("file.separator");
		if (rootpath == null)
		{
			
			IPath path = new Path(org.inria.mtl.MTLPlugin.getDefault().getLocation());
			System.setProperty("Directories.RootPath",
					getPathFromRelative(path.toOSString()) + "MTL" + sep + "bin");
			rootpath = System.getProperty("Directories.RootPath");
		}
		// Get the selected item in the view
		if (selection instanceof StructuredSelection)
		{
			IProject currentProject = null;
			currentSelection = (StructuredSelection)selection;
			// We assume (FIXME) that user can only select one project
			// disallow multi selection of projects?
			if (currentSelection.size()==1 
					&& currentSelection.getFirstElement() instanceof IProject)
			{
				currentProject = (IProject)currentSelection.getFirstElement();//.getProject?
				project_path = currentProject.getLocation().toOSString();
			}
		}

		// Get the necessary variable to execute the external antimtl python command
		String cmd_path = "python/src/org/irisa/antimtl/Antimtl.py";
		String templates_path = "python/templates";
		String options   = " --output="+project_path;	
		options 		+= " --templates_path="+this.getPathFromRelative(templates_path);
		
		// Try to execute the python external command, if a project was selected
		if (project_path!="")
		{
			Process py_proc;			// Here we ask the user to give the path of BasicMTLc.jar
			try
			{
				String ss = this.getPathFromRelative(cmd_path);
				InputDialog idialog = new InputDialog(shell, "get BasicMTLc.jar path", "Path of the _directory_ of BasicMTLc.jar...", 
						getDefaultBasicMTLcDir(),null);
				
				int code = idialog.open();
				// User can cancell her action
				if (code != InputDialog.CANCEL)
				{
					String i_basicmtlpath = idialog.getValue();
					options			+= " --jardir="+i_basicmtlpath;
					py_proc = Runtime.getRuntime().exec("python "
							+this.getPathFromRelative(cmd_path)
							+options);
					// Wheres we display the output of the python command 
					BufferedReader error_stream = new BufferedReader(
							new InputStreamReader(py_proc.getErrorStream()));
					BufferedReader input_stream = new BufferedReader(
							new InputStreamReader(py_proc.getInputStream()));
					while ((line = error_stream.readLine()) != null) 
						errstr += line;
					if (errstr!="")
						MessageDialog.openInformation(shell, "Information", "Errors : " + errstr);
				} 
			}
			catch (IOException e1)
			{
				System.err.println("Error during the execution of Antimtl.py command");
				e1.printStackTrace();
			}
		}
		else // if none selected, or too many selections, or selection is not a Project
		{
			MessageDialog.openInformation(shell, "Oups", "No selected project (or too many selections?)");
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection)
	{
		this.selection = selection;
		
	}

}
