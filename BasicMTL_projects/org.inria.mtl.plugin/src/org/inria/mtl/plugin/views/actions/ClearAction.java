/*
* $Id: ClearAction.java,v 1.1 2004-05-28 16:54:11 sdzale Exp $
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
		Controller.getInstance().clear();
	}
}