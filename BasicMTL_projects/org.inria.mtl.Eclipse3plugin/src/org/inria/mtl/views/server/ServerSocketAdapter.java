/*
* $Id: ServerSocketAdapter.java,v 1.2 2004-08-26 12:40:24 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.server;

import java.net.Socket;

/**
  * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ServerSocketAdapter implements IServerSocketListener
{
	abstract public void accept(Socket client);
	abstract public void objectReceived(Object o);
}
