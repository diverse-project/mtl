/*
* $Id: IControllerListener.java,v 1.1 2004-07-30 14:10:24 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.controller;

import org.inria.mtl.views.server.*;

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
