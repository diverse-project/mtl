/*
* $Id: ClearAction.java,v 1.1 2004-07-30 14:11:03 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.actions;

import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.views.Utility;
import org.inria.mtl.views.controller.Controller;

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
		//System.out.println("Clear Action ");
		//System.out.println("logfile length avant :"+Controller.getInstance().getLogfile().toArray().length);
		Controller.getInstance().clear();
		//System.out.println("logfile length :"+Controller.getInstance().getLogfile().toArray().length);
	}
}