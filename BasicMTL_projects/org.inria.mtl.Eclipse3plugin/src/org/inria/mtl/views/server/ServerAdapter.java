/*
* $Id: ServerAdapter.java,v 1.1 2004-07-30 14:08:54 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.server;

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
