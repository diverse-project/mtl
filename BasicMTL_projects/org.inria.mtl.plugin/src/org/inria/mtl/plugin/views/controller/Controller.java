/*
* $Id: Controller.java,v 1.1 2004-05-28 16:53:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.views.server.*;
import org.inria.mtl.plugin.views.MTLConsole;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class Controller
{
	private static Controller singleton;

	private final Set listeners = new HashSet();
	private MTLConsole view;
	private MTLPlugin plugin;
	private Logfile logfile;
	private Server server;

	public static Controller getInstance()
	{
		if (singleton == null)
		{
			singleton = new Controller();

			new Server();
			new Logfile();
		}

		return singleton;
	}

	public void acquaint(MTLConsole view)
	{
		this.view = view;
	}

	public void acquaint(MTLPlugin plugin)
	{
		this.plugin = plugin;
	}

	public void acquaint(Logfile logfile)
	{
		this.logfile = logfile;
	}

	public void acquaint(Server server)
	{
		this.server = server;

		
		server.addListener(new ServerAdapter()
		{
			public void serverUp()
			{
				fireServerUp();
			}

			public void serverDown()
			{
				fireServerDown();
			}
		});
		
		server.addServerSocketListener(new ServerSocketAdapter()
		{
			public void accept(Socket client)
			{
				System.out.println("Accepted connection from " + client.getInetAddress().getHostAddress());
			}

			public void objectReceived(Object o)
			{
				if (o instanceof Entry)
				{
					Entry e = (Entry) o;

					logfile.add(e);
					fireUpdate(e);
					System.out.println("add entry");
				}
			}
		});
	}

	//
	// Interface
	//
	public MTLPlugin getPlugin()
	{
		return plugin;
	}

	public Logfile getLogfile()
	{
		return logfile;
	}

	public void startServer() throws IOException
	{
		server.start();
	}

	public void stopServer() throws IOException
	{
		server.stop();
	}

	public void clear()
	{
		logfile.clear();
		view.refresh();
	}

	//
	// Listener
	//
	public void fireServerUp()
	{
		for (Iterator iter = listeners.iterator(); iter.hasNext(); )
			((IControllerListener) iter.next()).serverUp();
	}

	public void fireServerDown()
	{
		for (Iterator iter = listeners.iterator(); iter.hasNext(); )
			((IControllerListener) iter.next()).serverDown();
	}

	public void fireUpdate(Entry entry)
	{
		for (Iterator iter = listeners.iterator(); iter.hasNext(); )
			((IControllerListener) iter.next()).update(entry);
	}

	public void removeListener(IControllerListener listener)
	{
		listeners.remove(listener);
	}

	public void addListener(IControllerListener listener)
	{
		listeners.add(listener);
	}
}