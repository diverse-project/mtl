/*
 * Project  : org.inria.mtl.Eclipse3plugin
 * Filename : generateAntfilesAction.java
 * License  : GPL
 * Author   : zdrey@irisa.fr
 * 
 * Creation date     : Jan 19, 2005
 * Modification date : Jan 19, 2005
 * 
 * Description       :
 * 
 * This mini-project is intended for didactic purpose
 * as an example of simple compiler/interpreter.
 * 
 */
package org.inria.mtl.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @author zdrey
 * 
 * Description : TODO
 */
public class generateAntfilesAction implements IWorkbenchWindowActionDelegate {

	
	private IWorkbenchWindow window;
	private StructuredSelection currentSelection = null;
	private ISelection selection=null;
	private IProject currentProject = null;
	private IFolder srcFolder=null;

	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	
	protected String getProjectPath()
	{
		//String str = ResourcesPlugin.getPlugin().
		//File file = new File(myWorkspaceRoot.getProject("org.inria.mtl.Eclipse3plugin").getFullPath().toOSString());
		
//		System.out.println(str);
//		URL url = new URL(getBaseURL(), iconPath + name);
		System.out.println(new File("../../").getAbsolutePath());
	//	return new File("../../").toString() ;
		return "/udd/zdrey/Workspace/org.inria.mtl.Eclipse3plugin/python/src/org/irisa/antimtl/Antimtl.py";
	}
	
	/**
	 * Run the python Antimtl :
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		
		String cmd = this.getProjectPath();
		// Get the current directory?
		String options = " --cp=.classpath --mtlcp=.mtlclasspath --output=.";
		String line = ""; String str = ""; String errstr = "";
		try
		{
			Process py_proc = Runtime.getRuntime().exec("python "+cmd+options);
			Shell shell = window.getShell();
		    BufferedReader error_stream = new BufferedReader(
		    		new InputStreamReader(py_proc.getErrorStream()));
		    BufferedReader input_stream = new BufferedReader(
		    		new InputStreamReader(py_proc.getInputStream()));
		    
		    try {
		    	while ((line = error_stream.readLine()) != null) 
		    	{
		    		errstr += line;
		    	}
		    }
		    catch (IOException e)
			{
		    	System.exit(0);
		    }
		    
		    try {
		    	while ((line = input_stream.readLine()) != null) 
		    	{
		    		str += line;
		    	}
		    }
		    catch (IOException e)
			{
		    	System.exit(0);
		    }
		    
		    MessageDialog.openInformation(shell, "Information", "Errors : "+errstr);
		    MessageDialog.openInformation(shell, "Information", "StdOut : "+str);
		} 
		catch (IOException e)
		{
			System.err.println(e);
			System.exit(1);			
		}
		

		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection)
	{
		this.selection = selection;
		
	}

}
