package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;
import org.inria.mtl.MTLPlugin;
//import fr.univ.tours.EfE.EfEPlugin;

import java.net.MalformedURLException;
import java.net.URL;

public class MTLImages 
{
	
	//static final URL BASE_URL = MTLPlugin.getDefault().getDescriptor().getInstallURL();
	static final URL BASE_URL=MTLPlugin.instance().getBundle().getEntry("/");
	static final ImageDescriptor ICON_LIBRARY;
	static final ImageDescriptor ICON_MODEL;
	static final ImageDescriptor ICON_EDITOR;
	static final ImageDescriptor ICON_VIEW_LIBRARY_METHOD;
	static final ImageDescriptor ICON_VIEW_VARIABLE;
	static final ImageDescriptor ICON_VIEW_FUNCTION;
	static final ImageDescriptor ICON_VIEW_DYNAMIC_CLASS;
	static final ImageDescriptor ICON_VIEW_CLASS;
	static final ImageDescriptor ICON_VIEW_CLASS_METHOD;
	static final ImageDescriptor ICON_VIEW_INSTANCE_METHOD;
	static final ImageDescriptor ICON_VIEW_CLASS_VARIABLE;
	static final ImageDescriptor ICON_VIEW_INSTANCE_VARIABLE;

	static {
		String iconPath = "icons/";

		ICON_LIBRARY = createImageDescriptor(iconPath + "table.gif");
		ICON_MODEL = createImageDescriptor(iconPath + "model.gif");
		ICON_EDITOR = createImageDescriptor(iconPath + "sample.gif");
		ICON_VIEW_VARIABLE = createImageDescriptor(iconPath + "python.gif");
		ICON_VIEW_FUNCTION = createImageDescriptor(iconPath + "function.gif");
		ICON_VIEW_CLASS = createImageDescriptor(iconPath + "classe.gif");
		ICON_VIEW_DYNAMIC_CLASS = createImageDescriptor(iconPath + "string.gif");
		ICON_VIEW_CLASS_METHOD = createImageDescriptor(iconPath + "function.gif");
		ICON_VIEW_INSTANCE_METHOD = createImageDescriptor(iconPath + "inst.gif");
		ICON_VIEW_LIBRARY_METHOD =createImageDescriptor(iconPath + "inst.gif");
		ICON_VIEW_CLASS_VARIABLE = createImageDescriptor(iconPath + "constructor.gif");
		ICON_VIEW_INSTANCE_VARIABLE = createImageDescriptor(iconPath + "table.gif");
	}

	/**
	 * Utility method to create an <code>ImageDescriptor</code> from a path to a file.
	 * quant à sa réelle utilité....
	 */
	private static ImageDescriptor createImageDescriptor(String path)
	{
		try
		{
			URL url = new URL(BASE_URL, path);

			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e){
			//
		}

		return ImageDescriptor.getMissingImageDescriptor();
	}
}