/*
* $Id: Utility.java,v 1.1 2004-07-30 14:10:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.views.controller.Controller;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class Utility
{
	private final static HashMap images = new HashMap();
	private final static HashMap imageDescriptors = new HashMap();

	static
	{
		final String[] files = {"debug", "info", "warn", "error", "fatal", "start", "stop", "clear"};

		for (int i = 0; i < files.length; i++)
		{
			
			String file = files[i];
			//System.out.println("     Files   :"+file);
			imageDescriptors.put(file, loadImageDescriptor(file));
			//System.out.println("   put   Files   :"+loadImageDescriptor(file).toString());
			images.put(file, loadImage((ImageDescriptor) imageDescriptors.get(file)));
			//System.out.println("   load   Files   ");
		}
	}

	protected static Image loadImage(ImageDescriptor id)
	{
		return id.createImage();
	}

//	protected static ImageDescriptor loadImageDescriptor(String file)
//	{
//		try
//		{	System.out.println("Load image");
//			//URL url = new URL(Controller.getInstance().getPlugin().getDescriptor().getInstallURL(), "icons/" + file + ".gif");
//			System.out.println("Chemin Plugin:"+MTLPlugin.instance().getBaseURL().getPath());
//			URL url = new URL(MTLPlugin.getBaseURL(), "icons/" + file + ".gif");
//			System.out.println("Chemin :"+url.getPath());
//			return ImageDescriptor.createFromURL(url);
//		}
//		catch (MalformedURLException ex)
//		{
//		}
//
//		return null;
//	}

 		
	protected static ImageDescriptor loadImageDescriptor(String file)
	{
		try
		{	
			//System.out.println("Chemin");
			//System.out.println("Chemin Plugin:"+MTLPlugin.instance().getBundle().getEntry("/"));
			URL url = new URL(MTLPlugin.instance().getBundle().getEntry("/"), "icons/" + file + ".gif");
			return ImageDescriptor.createFromURL(url);
		}
		catch (MalformedURLException ex)
		{
		}

		return null;
	}

	
	public static ImageDescriptor getImageDescriptor(String key)
	{
		//System.out.println("Key :"+key);
		return (ImageDescriptor) imageDescriptors.get(key);
	}

	public static Image getImage(String key)
	{
		return (Image) images.get(key);
	}
}