/*
* $Id: LinkedPositionListener.java,v 1.2 2004-05-19 09:22:35 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.completion.link;

import org.eclipse.jface.text.Position;

/**
 * A listener for highlight change notification and exititing linked mode.
 */
public interface LinkedPositionListener {
	
	/**
	 * Notifies that the linked mode has been left. On success, all changes
	 * are kept, otherwise all changes made to the linked positions are restored
	 * to the state before entering linked mode.
	 */
	void exit(boolean success);
	
	/**
	 * Notifies the changed linked position. The listener is asked
	 * to reposition the caret at the given offset.
	 * 
	 * @param position    the linked position which initiated the change.
	 * @param caretOffset the caret offset relative to the position.
	 */
	void setCurrentPosition(Position position, int caretOffset);

}
