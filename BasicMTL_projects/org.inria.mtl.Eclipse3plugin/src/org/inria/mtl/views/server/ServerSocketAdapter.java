/*
* $Id: ServerSocketAdapter.java,v 1.1 2004-07-30 14:08:56 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.server;

import java.net.Socket;

/**
 * @author tcn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ServerSocketAdapter implements IServerSocketListener
{
	abstract public void accept(Socket client);
	abstract public void objectReceived(Object o);
}
