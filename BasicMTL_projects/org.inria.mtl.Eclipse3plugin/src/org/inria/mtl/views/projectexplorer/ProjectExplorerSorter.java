/*
 * Created on 28 oct. 2004
 *
 */
package org.inria.mtl.views.projectexplorer;

import org.eclipse.jface.viewers.ViewerSorter;
import org.inria.mtl.views.projectexplorer.model.ExternTLLContainer;
import org.inria.mtl.views.projectexplorer.model.RuntimeTLLContainer;
import org.inria.mtl.views.projectexplorer.model.UserTLLContainer;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;

/**
 * @author edrezen
 *
 */
public class ProjectExplorerSorter extends ViewerSorter
{
	/** */
	public int category (Object element) 
	{
		int result = 0;
		
		if (element instanceof RuntimeTLLContainer) 
		{
			result = 0;
		}

		else if (element instanceof ExternTLLContainer) 
		{
			result = 1;
		}

		else if (element instanceof UserTLLContainer) 
		{
			result = 2;
		}

		else if (element instanceof Library) 
		{
			result = 3;
		} 

		else if (element instanceof UserClass) 
		{
			result = 4;
		} 

		else if (element instanceof Attribute) 
		{
			result = 5;
		} 
		
		else if (element instanceof Operation) 
		{
			result = 6;
		}
		
		else if (element instanceof QualifiedName) 
		{
			result = 7;
		}


		return result;
	}

}
