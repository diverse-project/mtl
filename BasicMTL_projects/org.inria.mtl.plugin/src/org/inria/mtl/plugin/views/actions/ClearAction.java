/*
* $Id: ClearAction.java,v 1.2 2004-06-24 09:23:21 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.actions;

import org.inria.mtl.plugin.views.Utility;
import org.inria.mtl.plugin.views.controller.Controller;
import org.inria.mtl.plugin.views.MTLConsole;

import org.eclipse.jface.action.Action;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ClearAction extends Action
{
	private final MTLConsole view;
	private final static String CLEAR = "Clear";

	public ClearAction(MTLConsole view)
	{
		this.view = view;

		setText(CLEAR);
		setToolTipText(CLEAR);
		setImageDescriptor(Utility.getImageDescriptor("clear"));
	}

	public void run()
	{
		System.out.println("Clear Action ");
		System.out.println("logfile length avant :"+Controller.getInstance().getLogfile().toArray().length);
		Controller.getInstance().clear();
		System.out.println("logfile length :"+Controller.getInstance().getLogfile().toArray().length);
	}
}