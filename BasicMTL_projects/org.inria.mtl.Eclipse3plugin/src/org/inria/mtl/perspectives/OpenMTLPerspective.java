/*
* $Id: OpenMTLPerspective.java,v 1.1 2004-07-30 14:11:31 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.perspectives;

import org.inria.mtl.MTLPlugin;

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
			PlatformUI.getWorkbench().showPerspective("org.irisa.mtl.perspectives.MTLPerspective", page.getWorkbenchWindow());
		}
		catch(Throwable e)
		{
			MTLPlugin.error("Erreur Ouverture de la perspective MTL", e);
		}
	}
}
