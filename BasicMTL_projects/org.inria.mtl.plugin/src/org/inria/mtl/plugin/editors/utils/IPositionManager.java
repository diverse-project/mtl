package org.inria.mtl.plugin.editors.utils;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

import org.eclipse.jface.text.Position;


public interface IPositionManager {
	
	void addManagedPosition(Position position);
	
	void removeManagedPosition(Position position);
}
