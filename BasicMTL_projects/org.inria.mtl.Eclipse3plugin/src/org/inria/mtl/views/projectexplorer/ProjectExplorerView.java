/*
* $Id: ProjectExplorerView.java,v 1.1 2004-10-29 11:37:47 edrezen Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.views.projectexplorer;

import java.util.Collection;
import java.util.Observable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.part.FileEditorInput;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.inria.mtl.commands.build.AbstractBuildCommand;
import org.inria.mtl.utils.ViewPartWithTreeViewer;
import org.inria.mtl.views.projectexplorer.model.Container;
import org.inria.mtl.views.projectexplorer.model.ProjectExplorerModel;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/** This view allows to see all the libraries of a MTL project.
 */
public class ProjectExplorerView extends ViewPartWithTreeViewer implements java.util.Observer
{ 
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////

	private ActionGroup actionGroup;
	public ActionGroup getActionGroup() { return actionGroup; }
	protected void setActionGroup (ActionGroup actionGroup) { this.actionGroup = actionGroup; }

	private ProjectExplorerModel model;
	public ProjectExplorerModel getModel() { return model; }
	public void setModel(ProjectExplorerModel model) { this.model = model; }

	private IProject project;
	public IProject getProject() { return project; }

	private boolean firstSet = true;
	public void setProject(IProject project) 
	{
		if (project==null) { return; }

		this.project = project;
		
		if (firstSet)
		{
			firstSet = false;
			try {
				addAllTLL (getModel().getUserTLLContainer(), (Collection) MTLCommandExecutor.getUserTLL (getProject()) );
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static private  ProjectExplorerView instance;

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	
	/** The constructor. */
	public ProjectExplorerView () 
	{
		// we want to update this view when a build command occurs.
		AbstractBuildCommand.addClassifierObserver (this);
	}

	
	/** The Singleton */
	static public ProjectExplorerView instance()
	{
		if (instance==null)
		{
			instance = new ProjectExplorerView();
		}
		return instance;
	}
	

	////////////////////////////////////////////////////////////////////////////////
	// UI VIEW MANAGEMENT
	////////////////////////////////////////////////////////////////////////////////

	/** we create the graphical aspect of the view. 
	 */
	public void createPartControl (Composite parent) 
	{
		// first, we call the parent.
		super.createPartControl (parent);
	
		// we set attributes for the tree viewer
		getTreeViewer().setContentProvider (new ProjectExplorerContentProvider());
		getTreeViewer().setLabelProvider   (new ProjectExplorerLabelProvider());
		getTreeViewer().setSorter          (new ProjectExplorerSorter());
		
		getTreeViewer().setUseHashlookup(true);
		
		// we set the initial input
		getTreeViewer().setInput (getInitialInput());

		// we set the action group
		setActionGroup (new ProjectExplorerActionGroup(this));
		
		// we initialize the menu
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu (manager);
			}
		});
		Tree tree = getTreeViewer().getTree();
		Menu menu = menuMgr.createContextMenu (tree);
		tree.setMenu(menu);
		getSite().registerContextMenu(menuMgr, getTreeViewer());

	}

	/** */
	protected void fillContextMenu (IMenuManager menu) 
	{
		getActionGroup().setContext (new ActionContext(getCurrentSelection()));
		getActionGroup().fillContextMenu (menu);
	}

	
	/** Double click in this view.
	 * For some kind of selected item, it open a file and go to a specific line.
	 */
	protected void handleDoubleClick (DoubleClickEvent event) 
	{
		// we retrieve the first element in the selection
		Object item = getCurrentSelection().getFirstElement();

		if (item instanceof ASTNode)
		{
			ASTNode node = (ASTNode)item;
			
			// we retrieve the line number and the filename of the ASTNode
			Property lineNumber = node.getProperty ("LineNumber");
			Property fileName   = node.getProperty ("FileName");
		
			if (fileName==null || lineNumber==null) { return;}
			
			IPath path = new Path ((String) fileName.getValue());
			IFile file = MTLPlugin.getWorkspace().getRoot().getFileForLocation (path);
			
			if (file != null && file.exists())
			{
				int line = Integer.parseInt((String) lineNumber.getValue());
				
				try {
					MTLCommandExecutor.openFile (file);
					MTLCommandExecutor.gotoLine (line);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}				
		}
	}

	
	/** */
	private ProjectExplorerModel getInitialInput ()
	{
		ProjectExplorerModel result = new ProjectExplorerModel ();

		// we set the instance model
		setModel(result);
		
		// we load the Runtime TLL
		try {
			addAllTLL (getModel().getRuntimeTLLContainer(), (Collection) MTLCommandExecutor.getRuntimeTLL());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// we try to determine which project is involved.
		setProject (findInvolvedProject ());
		
		// we return the result
		return result;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// OBSERVER/OBSERVABLE MANAGEMENT
	////////////////////////////////////////////////////////////////////////////////
	
	/** This view can be an observer wrt some observables.
	 * It should react on some notifications. 
	 */
	public void update (Observable o, Object arg) 
	{
		// we look for a specific kind of notification
		final IFile tllFile = isAfterFolderCompileNotification (o,arg);
		if (tllFile != null)
		{
			// we update the display
			
			getTreeViewer().getTree().getDisplay().asyncExec (new Runnable()
			{
				public void run() {			

					// we have to check that the TLL has been correctly generated
					if (tllFile.exists())
					{
						// we can retrieve the project from the tll file
						setProject (tllFile.getProject());
	
						// we load the library from the tll file
						addTLL (getModel().getUserTLLContainer(), tllFile.getLocation().toOSString());
						
					}
					else
					{
						// we get the name of the TLL from the IFile
						String name = tllFile.getFullPath().removeFileExtension().lastSegment().toString();
						
						//we have to remove the (potentially) existing TLL
						getModel().getUserTLLContainer().removeFromName (name);
					}

					// we refresh the input
					getTreeViewer().refresh();
				}
			});
		}
	}

	
	/** A little helper to recognize a specific kind of notification.
	 * The result is an IFile on the generated TLL. 
	 */
	private IFile isAfterFolderCompileNotification (Observable o, Object arg)
	{
		IFile result = null;
		if (o instanceof AbstractBuildCommand)
		{
			if (arg instanceof Object[])
			{
				Object[] args = (Object[]) arg;
				if (args[0]=="after")
				{
					String tllName  = (String)args[1];
					IFolder tllPath = (IFolder)args[2];
					result = tllPath.getFile (tllName + ".tll");
				}
			}
		}
		return result;
	}


	////////////////////////////////////////////////////////////////////////////////
	// TOOLS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	private void addAllTLL (Container container, java.util.Collection files)
	{
		for (java.util.Iterator it=files.iterator(); it.hasNext(); )
		{
			String item = (String) it.next();
			addTLL (container, item);
		}
	}

	/** */
	private void addTLL (Container container, String path)
	{
		container.add (Library.load (path));
	}

	/** */
	private IProject findInvolvedProject () 
	{
		// First attempt
		IWorkbenchPage page = MTLPlugin.getActivePage();
		if (page != null)
		{
			IEditorPart editor = page.getActiveEditor();
			if (editor != null)
			{
				IEditorInput editorInput = editor.getEditorInput();
				if (editorInput != null && editorInput instanceof FileEditorInput)
				{
					FileEditorInput fileInput = (FileEditorInput)editorInput;
					return fileInput.getFile().getProject();
				}
			}
		}

		// we return the result
		return null;
	}
}
