/*
* $Id: Server.java,v 1.2 2004-06-18 14:20:39 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
//import java.io.*;

import org.inria.mtl.plugin.views.controller.Controller;
import org.inria.mtl.plugin.preferences.Log4jPreferencePage;

import org.apache.log4j.spi.LoggingEvent;

/**
 * A daemon thread that processes connections from a <code>org.apache.log4j.net.SocketAppender.html</code>.
 * 
 * @author <a href="mailto:log4eclipse@nitwit.de">Timo Nentwig</a>
 */
public class Server implements Runnable
{
	private final Set listeners = new HashSet();
	private final Set serverSocketListeners = new HashSet();
	
	private volatile Thread thread;
	private ServerSocket server;
	private int port;

	/**
	 *  
	 */
	public Server()
	{
		Controller.getInstance().acquaint(this);
	}

	/**
	 * @throws IOException
	 */
	public void start() throws IOException
	{	
		server = new ServerSocket(Controller.getInstance().getPlugin().getPreferenceStore().getInt(Log4jPreferencePage.P_PORT));
		server.setReuseAddress(true);
		
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
		fireServerUp();
	}

	/**
	 * @throws IOException
	 */
	public void stop() throws IOException
	{
		server.close();
		server = null;
		thread = null;

		fireServerDown();
	}

	/**
	 * Listen for client connections
	 */
	public void run()
	{
		try
		{
			while (true)
			{
				final Socket client = server.accept();				
				final Thread t = new Thread(new SocketListener(client));
				t.setDaemon(true);
				t.start();
			}
		}
		catch (IOException e)
		{
			System.out.println("Server: " + e);
		}
	}

	//
	// ServerListener
	//
	public void removeListener(IServerListener listener)
	{
		listeners.remove(listener);
	}

	public void addListener(IServerListener listener)
	{
		listeners.add(listener);
	}

	public void fireServerUp()
	{
		for (Iterator iter = listeners.iterator(); iter.hasNext(); ){
			((IServerListener) iter.next()).serverUp();
			
		}
	}

	public void fireServerDown()
	{
		for (Iterator iter = listeners.iterator(); iter.hasNext(); )
			((IServerListener) iter.next()).serverDown();
	}

	/**
	 * Helper that actually processes a particular client connection. It
	 * receives events and adds them to the supplied model.
	 * 
	 * @author <a href="mailto:log4eclipse@nitwit.de">Timo Nentwig</a>
	 */
	private class SocketListener implements Runnable
	{
		private final Socket client;

		/**
		 * 
		 * @param client
		 *                   socket to receive events from
		 */
		private SocketListener(Socket client)
		{
			this.client = client;
		}
	
		public void run()
		{
			Server.this.fireAccept(client);
			

			try
			{
				final ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

				while (true)
				{
					Server.this.fireObjectReceived(new Entry((LoggingEvent) ois.readObject()));
				}
			}
			catch (Exception e)
			{
				System.out.println("SocketListener: " + e);
			}
			finally
			{
				try
				{
					client.close();
				}
				catch (IOException e)
				{
					System.out.println(e);
				}
			}
		}
	}

	//
	// ServerSocketListener
	//
	public void removeServerSocketListener(IServerSocketListener listener)
	{
		serverSocketListeners.remove(listener);
	}

	public void addServerSocketListener(IServerSocketListener listener)
	{
		serverSocketListeners.add(listener);
	}

	public void fireAccept(Socket client)
	{
		for (Iterator iter = serverSocketListeners.iterator(); iter.hasNext(); ){
			((IServerSocketListener) iter.next()).accept(client);
		}
	}

	public void fireObjectReceived(Object o)
	{
		for (Iterator iter = serverSocketListeners.iterator(); iter.hasNext(); )
			((IServerSocketListener) iter.next()).objectReceived(o);
	}

}