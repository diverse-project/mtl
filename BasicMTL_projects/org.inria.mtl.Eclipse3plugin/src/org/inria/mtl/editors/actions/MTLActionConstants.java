/*
* $Id: MTLActionConstants.java,v 1.2 2004-08-26 12:40:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.actions;

/**
 * Action ids for standard actions, for groups in the menu bar, and
 * for actions in context menus of MTL views.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
  */
public class MTLActionConstants {

  //	Edit menu

  /**
   * Edit menu: name of standard Code Assist global action
   * (value <code>"org.inria.mtl.editor.actions.ContentAssist"</code>).
   */
  public static final String CONTENT_ASSIST = "org.inria.mtl.editors.ContentAssist"; //$NON-NLS-1$

  // Source menu	

  /**
   * Source menu: name of standard Comment global action
   * (value <code>"org.inria.mtl.editors.actions.Comment"</code>).
   */
  public static final String COMMENT = "org.inria.mtl.mtleditor.Comment"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Uncomment global action
   * (value <code>"org.inria.mtl.editors.Uncomment"</code>).
   */
  public static final String UNCOMMENT = "org.inria.mtl.mtleditor.UnComment"; //$NON-NLS-1$
  /**
	 * Source menu: name of standard Shift Rightl action
	 * (value <code>"org.inria.mtl.editors.ShiftRight"</code>).
	 */
  public static final String SHIFT_RIGHT = "org.inria.mtl.editors.ShiftRight"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Shift Left global action
   * (value <code>"org.inria.mtl.editors.ShiftLeft"</code>).
   */
  public static final String SHIFT_LEFT = "org.inria.mtl.editors.ShiftLeft"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Format global action (value <code>"org.
   * phpeclipse.phpdt.ui.actions.Format"</code>).
   */
  public static final String FORMAT = "org.inria.mtl.mtleditor.Format "; //$NON-NLS-1$

 
  /**
   * Navigate menu: name of standard Open global action
   * (value <code>"org.inria.mtl.editors.Open"</code>).
   */
  public static final String OPEN = "org.inria.mtl.editors.Open"; //$NON-NLS-1$

}
