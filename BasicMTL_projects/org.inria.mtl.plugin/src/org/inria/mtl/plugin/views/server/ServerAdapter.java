/*
* $Id: ServerAdapter.java,v 1.1 2004-05-28 16:52:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.server;

/**
 * @author tcn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ServerAdapter implements IServerListener
{
	abstract public void serverUp();
	abstract public void serverDown();
}
