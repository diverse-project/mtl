/*
* $Id: ControllerAdapter.java,v 1.2 2004-08-26 12:40:56 sdzale Exp $
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
public abstract class ControllerAdapter implements IControllerListener
{
	abstract public void serverUp();
	abstract public void serverDown();
	abstract public void update(Entry entry);
}
