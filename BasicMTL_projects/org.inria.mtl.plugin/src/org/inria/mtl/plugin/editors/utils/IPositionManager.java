/*
* $Id: IPositionManager.java,v 1.2 2004-05-19 09:22:08 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;


import org.eclipse.jface.text.Position;


public interface IPositionManager {
	
	void addManagedPosition(Position position);
	void removeManagedPosition(Position position);
}