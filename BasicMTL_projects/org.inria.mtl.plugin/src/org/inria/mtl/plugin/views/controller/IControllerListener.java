/*
* $Id: IControllerListener.java,v 1.1 2004-05-28 16:53:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.controller;

import org.inria.mtl.plugin.views.server.*;

/**
 * @author tcn
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface IControllerListener
{
	public void serverUp();
	public void serverDown();

	public void update(Entry entry);
}
