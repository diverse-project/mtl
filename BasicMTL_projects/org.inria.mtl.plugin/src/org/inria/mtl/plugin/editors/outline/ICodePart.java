/*
* $Id: ICodePart.java,v 1.2 2004-05-19 09:21:11 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.outline;

import org.eclipse.jface.text.Position;

/**
 * @author sdzale
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface ICodePart {
	public String getTitle();
	public ICodePart[] getChildern();
	public Object getParent();
	public void addChild(ICodePart child);
	public void setParent(Object parent);
	public Position getPosition();
	public void setPosition(Position p);
}
