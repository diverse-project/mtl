package org.inria.mtl.views.projectexplorer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.inria.mtl.views.projectexplorer.model.ExternTLLContainer;
import org.inria.mtl.views.projectexplorer.model.ProjectExplorerModel;
import org.inria.mtl.views.projectexplorer.model.RuntimeTLLContainer;
import org.inria.mtl.views.projectexplorer.model.UserTLLContainer;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

public class ProjectExplorerContentProvider implements ITreeContentProvider
{
	/** This method builds what the content of a node should be. 
	 */
	public Object[] getChildren (Object parent) 
	{
		// it is easier to work with collections than arrays
		// since we don't kwow before starting the number of children
		java.util.Collection result = new java.util.Vector ();

		////////////////////////////////////////
		// The parent is a Collection of Libaries
		////////////////////////////////////////
		if (parent instanceof ProjectExplorerModel)
		{
			ProjectExplorerModel model = (ProjectExplorerModel)parent;

			result.add (model.getRuntimeTLLContainer());
			//result.add (model.getExternTLLContainer());
			result.add (model.getUserTLLContainer());
		}

		////////////////////////////////////////
		// The parent is a RuntimeTLLContainer
		////////////////////////////////////////
		if (parent instanceof RuntimeTLLContainer)
		{
			RuntimeTLLContainer container = (RuntimeTLLContainer)parent;
			for (java.util.Iterator it=container.iterator(); it.hasNext(); )
			{
				result.add (it.next());
			}
		}
		
		////////////////////////////////////////
		// The parent is a ExternTLLContainer
		////////////////////////////////////////
		if (parent instanceof ExternTLLContainer)
		{
			ExternTLLContainer container = (ExternTLLContainer)parent;
			for (java.util.Iterator it=container.iterator(); it.hasNext(); )
			{
				result.add (it.next());
			}
		}

		////////////////////////////////////////
		// The parent is a UserTLLContainer
		////////////////////////////////////////
		if (parent instanceof UserTLLContainer)
		{
			UserTLLContainer container = (UserTLLContainer)parent;
			for (java.util.Iterator it=container.iterator(); it.hasNext(); )
			{
				result.add (it.next());
			}
		}

		////////////////////////////////////////
		// The parent is a TheLibraryClass
		////////////////////////////////////////
		if (parent instanceof Library)
		{
			Library library = (Library) parent;
			TheLibraryClass libraryClass = null; 
			
			// we loop over the types of the library
			KnownClasses classes = library.getKnownTypes();
			if (classes != null)
			{
				for (java.util.Enumeration enum = classes.keys(); enum.hasMoreElements(); )
				{
					Object item = classes.get (enum.nextElement());

					if (! (item instanceof TheLibraryClass))
					{
						result.add (item);
					}
					else 
					{
						libraryClass = (TheLibraryClass)item;
					}
				}
			}
			
			// we loop over the operations of the library
			if (libraryClass != null)
			{
				java.util.Collection features = libraryClass.definedFeatures;
				for (java.util.Iterator it=features.iterator(); it.hasNext(); )
				{
					result.add (it.next());
				}
			}
			
		}
		
		////////////////////////////////////////
		// The parent is a UserClass
		////////////////////////////////////////
		if (parent instanceof UserClass)
		{
			UserClass userClass = (UserClass) parent;
			
			java.util.Collection features = userClass.definedFeatures;
			for (java.util.Iterator it=features.iterator(); it.hasNext(); )
			{
				result.add (it.next());
			}
		}

		////////////////////////////////////////
		// The parent is a Feature
		////////////////////////////////////////
		if (parent instanceof Operation)
		{
			Operation operation = (Operation) parent;

			OpSignature signature = operation.getTheSignature();
			for (int i=0; i<signature.getArgsCount(); i++)
			{
				result.add (signature.getArgsTypes(i));
			}
		}

		
		// we return the result (we convert the collection into an array)
		return result.toArray(new Object[result.size()]);
	}

	
	/** */
	public Object getParent (Object element) 
	{
		return null;
	}

	/** */
	public boolean hasChildren (Object element) 
	{
		return getChildren(element).length > 0;
	}

	/** */
	public Object[] getElements (Object inputElement) 
	{
		return getChildren(inputElement);
	}

	/** */
	public void dispose() 
	{
	}

	
	/** */
	public void inputChanged (Viewer viewer, Object oldInput, Object newInput) 
	{
		if ( (oldInput != null) && (oldInput instanceof ProjectExplorerModel) )
		{
			// we have retrieved the previous model
			ProjectExplorerModel oldModel = (ProjectExplorerModel)oldInput;
			
			if ( (newInput != null) && (newInput instanceof ProjectExplorerModel) )
			{
				// we have retrieved the previous model
				ProjectExplorerModel newModel = (ProjectExplorerModel)newInput;
			}
		}
	}
}
