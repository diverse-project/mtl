/*
* $Id: IControllerListener.java,v 1.2 2004-08-26 12:40:57 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.controller;

import org.inria.mtl.views.server.*;

/**
  * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface IControllerListener
{
	public void serverUp();
	public void serverDown();

	public void update(Entry entry);
}
