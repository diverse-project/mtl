/*
* $Id: Controller.java,v 1.2 2004-08-26 12:40:57 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.inria.mtl.MTLPlugin;
import org.inria.mtl.views.MTLConsole;
import org.inria.mtl.views.server.*;

/**
 * @author sdzale
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
					System.out.println("add entry :"+e.getMessage()+"  "+e.getLevel().toString()+"  "+e.getLoggerName());

					logfile.add(e);
					fireUpdate(e);
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
		System.out.println("VIEW REFRESH");
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
		System.out.println("LISTENER ");
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