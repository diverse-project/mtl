/*
* $Id: IPainter.java,v 1.2 2004-05-19 09:22:14 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

public interface IPainter {

  /** Paint reasons */
  int SELECTION=		0;
  int TEXT_CHANGE=	1;
  int KEY_STROKE=		2;
  int MOUSE_BUTTON= 4;
  int INTERNAL=			8;
  int CONFIGURATION= 16;
	

  /**	
   * Disposes this painter.
   * <p>
   * XXX: The relationship with deactivate is not yet defined.
   * </p>
   * */
  void dispose();
	
  void paint(int reason);

  /**
   * Deactivates the painter.
   * <p>
   * XXX: The relationship with dispose is not yet defined.
   * </p>
   */
  void deactivate(boolean redraw);
	
  void setPositionManager(IPositionManager manager);
}
 
