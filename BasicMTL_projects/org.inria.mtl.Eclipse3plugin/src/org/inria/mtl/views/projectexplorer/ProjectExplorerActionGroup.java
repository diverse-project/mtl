/*
 * Created on 28 oct. 2004
 *
 */
package org.inria.mtl.views.projectexplorer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.ActionGroup;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.inria.mtl.views.projectexplorer.model.UserTLLContainer;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;


/**
 * @author edrezen
 *
 */
public class ProjectExplorerActionGroup extends ActionGroup  
{
	////////////////////////////////////////////////////////////////////////////////
	// INNER CLASSES
	////////////////////////////////////////////////////////////////////////////////
	public class CompileLibraryAction extends Action
	{
		/** */
		public String getText() { return "Compile Library"; }

		/** */
		public void run() 
		{
			Object selection = ((IStructuredSelection)getContext().getSelection()).getFirstElement();
			if (selection instanceof Library)
			{
				Library library = (Library)selection;
				IFolder folder = getFolderFromLibraryName (library.getName());
				if (folder.exists())
				{
					try {
						MTLCommandExecutor.buildFolder (folder);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		/** */
		private IFolder getFolderFromLibraryName(String name) 
		{
			IFolder result = null;

			// we loop over all the source folders (given by their IPath)
			IPath[] folders = MTLModel.srcFolders;
			IPath found = null;
			for (int i=0; i<folders.length && found==null; i++)
			{
				if (folders[i].lastSegment().equals(name))  { found = folders[i]; }
			}
			
			if (found != null)
			{
				result = MTLPlugin.getWorkspace().getRoot().getFolder (found);
			}
			
			return result;
		}
	}
	
	
	public class CompileProjectAction extends Action
	{
		/** */
		public String getText() { return "Compile project"; }

		/** */
		public void run() 
		{
			if (MTLModel.proj.exists())
			{
				try {
					MTLCommandExecutor.buildProject (MTLModel.proj);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public class CompileFullProjectAction extends Action
	{
		/** */
		public String getText() { return "Compile full project"; }

		/** */
		public void run() 
		{
			if (MTLModel.proj.exists())
			{
				try {
					MTLCommandExecutor.buildFullProject(MTLModel.proj);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private ProjectExplorerView explorer;
	public ProjectExplorerView getExplorer() { return explorer; }
	public void setExplorer(ProjectExplorerView explorer) { this.explorer = explorer; }

	private CompileLibraryAction compileLibraryAction = new CompileLibraryAction(); 
	public CompileLibraryAction getCompileLibraryAction() { return compileLibraryAction; }
	public void setCompileLibraryAction(CompileLibraryAction compileLibraryAction) { this.compileLibraryAction = compileLibraryAction; }
	
	private CompileProjectAction compileProjectAction = new CompileProjectAction ();
	public CompileProjectAction getCompileProjectAction() { return compileProjectAction; }
	public void setCompileProjectAction(CompileProjectAction compileProjectAction) { this.compileProjectAction = compileProjectAction; }

	private CompileFullProjectAction compileFullProjectAction = new CompileFullProjectAction ();
	public CompileFullProjectAction getCompileFullProjectAction() { return compileFullProjectAction; }
	public void setCompileFullProjectAction(CompileFullProjectAction compileFullProjectAction) { this.compileFullProjectAction = compileFullProjectAction; }

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public ProjectExplorerActionGroup (ProjectExplorerView explorer)
	{
		setExplorer (explorer);
	}
	
	
	/** */
	public void fillContextMenu(IMenuManager menu) 
	{
		IStructuredSelection selection = (IStructuredSelection)getContext().getSelection();
		Object element = selection.getFirstElement();
		
		if (element instanceof Library) 
		{
			menu.add (getCompileLibraryAction());
		}
		
		if (element instanceof UserTLLContainer)
		{
			menu.add (getCompileProjectAction());
			menu.add (getCompileFullProjectAction());
		}
	}

}
