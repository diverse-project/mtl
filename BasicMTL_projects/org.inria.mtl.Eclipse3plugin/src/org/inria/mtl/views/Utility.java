/*
* $Id: Utility.java,v 1.2 2004-08-26 12:40:47 sdzale Exp $
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
			imageDescriptors.put(file, loadImageDescriptor(file));
			images.put(file, loadImage((ImageDescriptor) imageDescriptors.get(file)));
			}
	}

	protected static Image loadImage(ImageDescriptor id)
	{
		return id.createImage();
	}

 		
	protected static ImageDescriptor loadImageDescriptor(String file)
	{
		try
		{	
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
		return (ImageDescriptor) imageDescriptors.get(key);
	}

	public static Image getImage(String key)
	{
		return (Image) images.get(key);
	}
}