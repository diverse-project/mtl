/*
* $Id: IPositionManager.java,v 1.1 2004-07-30 14:08:16 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;


import org.eclipse.jface.text.Position;


public interface IPositionManager {
	
	void addManagedPosition(Position position);
	void removeManagedPosition(Position position);
}