/*
* $Id: ServerAction.java,v 1.1 2004-05-28 16:54:11 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.actions;

import java.io.IOException;

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
public class ServerAction extends Action
{
	private final MTLConsole view;
	private boolean isRunning = false;
	private final static String START = "Start server", STOP = "Stop server";

	public ServerAction(MTLConsole view)
	{
		this.view = view;
		setRunning(false);
	}

	public void run()
	{
		System.out.println("Server action");
		Controller controller = Controller.getInstance();

		if (isRunning)
		{
			try
			{
				controller.stopServer();
			}
			catch (IOException e)
			{
				view.showMessage("Failed to start server: " + e);
				return;
			}
		}
		else
		{
			try
			{
				controller.startServer();
			}
			catch (IOException e)
			{
				view.showMessage("Failed to stop server: " + e);
				return;
			}
		}
	}

	/**
	 * @param b
	 */
	public void setRunning(boolean b)
	{
		isRunning = b;

		if (isRunning)
		{
			setText(STOP);
			setToolTipText(STOP);
			setImageDescriptor(Utility.getImageDescriptor("stop"));
		}
		else
		{
			setText(START);
			setToolTipText(START);
			setImageDescriptor(Utility.getImageDescriptor("start"));
		}
	}
}