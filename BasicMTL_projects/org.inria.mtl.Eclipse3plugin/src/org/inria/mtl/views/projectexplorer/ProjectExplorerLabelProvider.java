package org.inria.mtl.views.projectexplorer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.views.projectexplorer.model.ExternTLLContainer;
import org.inria.mtl.views.projectexplorer.model.RuntimeTLLContainer;
import org.inria.mtl.views.projectexplorer.model.UserTLLContainer;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;

public class ProjectExplorerLabelProvider extends LabelProvider 
{	
	private Map theMap = new HashMap (8);
	private Map imageCache = new HashMap(8);
	
	/** */
	public ProjectExplorerLabelProvider ()
	{
		theMap.put (BasicMtlLibrary.class,	new Object[] {"SimpleMCI/package_obj.gif"});
		theMap.put (UserClass.class, 		new Object[] {"SimpleMCI/class_obj.gif"});
		theMap.put (Attribute.class, 		new Object[] {"SimpleMCI/field_public_obj.gif"});
		theMap.put (Operation.class, 		new Object[] {"SimpleMCI/methpub_obj.gif"});
		theMap.put (QualifiedName.class, 	new Object[] {"SimpleMCI/parameter_obj.gif"});
		
		theMap.put (RuntimeTLLContainer.class, 		new Object[] {"SimpleMCI/runtimeTLL_obj.gif"});
		theMap.put (ExternTLLContainer.class, 		new Object[] {"SimpleMCI/externTLL_obj.gif"});
		theMap.put (UserTLLContainer.class, 		new Object[] {"SimpleMCI/userTLL_obj.gif"});
	}
	
	
	/** */
	public Image getImage (Object element) 
	{
		Image image = null;

		// we try to retrieve information about the Class of the given element
		Object value = theMap.get (element.getClass());

		if (value != null)
		{
			Object[] args = (Object[])value;
			
			ImageDescriptor descriptor = MTLPlugin.getImageDescriptor((String) args[0]);
			image = (Image)imageCache.get(descriptor);
			if (image == null) 
			{
				image = descriptor.createImage();
				imageCache.put(descriptor, image);
			}
		}

		return image;
	}

	
	/** */
	public String getText (Object element) 
	{
		if (element instanceof Library) 
		{
			return ((Library)element).getName();
		} 

		else if (element instanceof Attribute) 
		{
			return ((Attribute)element).getName();
		} 
		
		else if (element instanceof UserClass) 
		{
			return ((UserClass)element).getName();
		} 
		
		else if (element instanceof Operation) 
		{
			return ((Operation)element).getName (); 
		}
		
		else if (element instanceof QualifiedName) 
		{
			QualifiedName qname = (QualifiedName)element;
			
			return qname.toString() ;
		}

		else if (element instanceof RuntimeTLLContainer) 
		{
			return "Runtime TLL";
		}

		else if (element instanceof ExternTLLContainer) 
		{
			return "Extern TLL";
		}

		else if (element instanceof UserTLLContainer) 
		{
			return "Project TLL";
		}

		else 
		{
			return "UNKNOWN...";
		}
	}

	
	/** */
	public void dispose() 
	{
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) 
		{
			((Image) i.next()).dispose();
		}
		imageCache.clear();
	}
}
