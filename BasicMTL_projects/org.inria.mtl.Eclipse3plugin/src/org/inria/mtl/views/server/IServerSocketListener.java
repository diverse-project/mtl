/*
* $Id: IServerSocketListener.java,v 1.1 2004-07-30 14:08:56 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.server;

import java.net.Socket;
import java.util.EventListener;

/**
 * @author tcn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface IServerSocketListener extends EventListener
{
	public void accept(Socket client);
	
	public void objectReceived(Object o);
}