/*
* $Id: ViewContentProvider.java,v 1.2 2004-08-26 12:40:46 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views;

import org.inria.mtl.views.controller.Controller;
import org.inria.mtl.views.server.Entry;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 
 * The content provider class is responsible for providing objects to the view.
 * It can wrap existing objects in adapters or simply return objects as-is.
 * These objects may be sensitive to the current input of the view, or ignore
 * it and always show the same content (like Task List, for example).
 */
public class ViewContentProvider implements IStructuredContentProvider
{
	public ViewContentProvider()
	{
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput)
	{
	}

	public void dispose()
	{
	}

	public Object[] getElements(Object parent)
	{
		return Controller.getInstance().getLogfile().toArray();
	}

	public void add(Entry entry)
	{
		// the actual contentProvider is Logfile which is in the model where
		// it does belong to. So, we don't add here since it has already been
		// added.
	}
}