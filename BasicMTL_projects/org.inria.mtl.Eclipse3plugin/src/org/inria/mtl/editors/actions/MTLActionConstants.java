/*
* $Id: MTLActionConstants.java,v 1.1 2004-07-30 14:08:47 sdzale Exp $
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
   * Source menu: name of standard Convert Line Delimiters To Windows global action
   * (value <code>"org.inria.mtl.editors.ConvertLineDelimitersToWindows"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_WINDOWS = "org.inria.mtl.editors.ConvertLineDelimitersToWindows"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Convert Line Delimiters To UNIX global action
   * (value <code>"org.inria.mtl.editors.ConvertLineDelimitersToUNIX"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_UNIX = "org.inria.mtl.editors.ConvertLineDelimitersToUNIX"; //$NON-NLS-1$

  /**
   * Source menu: name of standardConvert Line Delimiters ToMac global action
   * (value <code>"org.inria.mtl.editors.ConvertLineDelimitersToMac"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_MAC = "org.inria.mtl.editors.ConvertLineDelimitersToMac"; //$NON-NLS-1$

  /**
   * Navigate menu: name of standard Open global action
   * (value <code>"org.inria.mtl.editors.Open"</code>).
   */
  public static final String OPEN = "org.inria.mtl.editors.Open"; //$NON-NLS-1$

}
