/*
* $Id: ServerAction.java,v 1.2 2004-08-26 12:41:02 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.actions;

import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.views.Utility;
import org.inria.mtl.views.controller.Controller;

/**
* Action that start or stop the server
 */
public class ServerAction extends Action
{
	public boolean isRunning = false;
	private final MTLConsole view;
	private final static String START = "Start server", STOP = "Stop server";
	
	private static ServerAction instance = new ServerAction(new MTLConsole());

	public ServerAction(MTLConsole fview)
	{
		this.view = fview;
		setRunning(false);
		}
	
	public static ServerAction getInstance() {
			return instance;
	}

	public void run()
	{
		
		Controller controller = Controller.getInstance();
		if (isRunning)
		{
			try
			{
				
				controller.stopServer();
			}
			catch (IOException e)
			{
				System.out.println("Failed to start server: " + e);
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
				System.out.println("Failed to stop server: " + e);
				return;
			}
		}
	}

	/**
	 * @param b
	 */
	public  void setRunning(boolean b)
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