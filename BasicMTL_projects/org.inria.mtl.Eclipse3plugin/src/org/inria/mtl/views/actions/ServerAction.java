/*
* $Id: ServerAction.java,v 1.1 2004-07-30 14:11:02 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.actions;

import java.io.IOException;

import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.views.Utility;
import org.inria.mtl.views.controller.Controller;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
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
		//System.out.println("ConsServerAction");
	}
	
	public static ServerAction getInstance() {
			return instance;
	}

	public void run()
	{
		
		Controller controller = Controller.getInstance();
		System.out.println("Server is running :" + isRunning);
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
		//System.out.println("SET RUUNNING");
		isRunning = b;
		//System.out.println("IsRunning :"+b);

		if (isRunning)
		{
			//System.out.println("IsRunning true 0 ");
			setText(STOP);
			setToolTipText(STOP);
			setImageDescriptor(Utility.getImageDescriptor("stop"));
			
		}
		else
		{
			setText(START);
			setToolTipText(START);
			//System.out.println("IsRunning False");
			//ImageDescriptor i=Utility.getImageDescriptor("start");
			//System.out.println("ImageDesc :"+(i==null?"nulle":"non null"));
			setImageDescriptor(Utility.getImageDescriptor("start"));
		}
	}
}