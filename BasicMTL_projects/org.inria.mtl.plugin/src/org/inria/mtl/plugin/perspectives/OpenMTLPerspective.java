/*
* $Id: OpenMTLPerspective.java,v 1.2 2004-05-19 09:22:52 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.perspectives;

import org.inria.mtl.plugin.MTLPlugin;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class OpenMTLPerspective extends Action {
	public void run()
	{
		IWorkbenchPage page = MTLPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if(page == null)
			return;
		try
		{
			PlatformUI.getWorkbench().showPerspective("org.irisa.mtl.plugin.perspectives.MTLPerspective", page.getWorkbenchWindow());
		}
		catch(Throwable e)
		{
			MTLPlugin.error("Erreur Ouverture de la perspective MTL", e);
		}
	}
}
