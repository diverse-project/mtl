/*
* $Id: MTLActionConstants.java,v 1.2 2004-05-19 09:21:59 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

/**
 * Action ids for standard actions, for groups in the menu bar, and
 * for actions in context menus of PHPDT views.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 
 */
public class MTLActionConstants {

  //	Edit menu

  /**
   * Edit menu: name of standard Code Assist global action
   * (value <code>"org.phpeclipse.phpdt.ui.actions.ContentAssist"</code>).
   */
  public static final String CONTENT_ASSIST = "org.irisa.mtl.plugin.editor.ContentAssist"; //$NON-NLS-1$

  // Source menu	

  /**
   * Source menu: name of standard Comment global action
   * (value <code>"net.sourceforge.phpdt.ui.actions.Comment"</code>).
   */
  public static final String COMMENT = "org.irisa.mtl.plugin.editor.Comment"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Uncomment global action
   * (value <code>"net.sourceforge.phpdt.ui.actions.Uncomment"</code>).
   */
  public static final String UNCOMMENT = "org.irisa.mtl.plugin.editor.Uncomment"; //$NON-NLS-1$
  /**
	 * Source menu: name of standard Shift Rightl action
	 * (value <code>"net.sourceforge.phpeclipse.phpeditor.ShiftRight"</code>).
	 */
  public static final String SHIFT_RIGHT = "org.irisa.mtl.plugin.editor.ShiftRight"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Shift Left global action
   * (value <code>"net.sourceforge.phpeclipse.phpeditor.ShiftLeft"</code>).
   */
  public static final String SHIFT_LEFT = "org.irisa.mtl.plugin.editor.ShiftLeft"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Format global action (value <code>"org.
   * phpeclipse.phpdt.ui.actions.Format"</code>).
   */
  public static final String FORMAT = "org.irisa.mtl.plugin.editor.Format"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Convert Line Delimiters To Windows global action
   * (value <code>"org.phpeclipse.phpdt.ui.actions.ConvertLineDelimitersToWindows"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_WINDOWS = "net.sourceforge.phpeclipse.ui.actions.ConvertLineDelimitersToWindows"; //$NON-NLS-1$

  /**
   * Source menu: name of standard Convert Line Delimiters To UNIX global action
   * (value <code>"org.phpeclipse.phpdt.ui.actions.ConvertLineDelimitersToUNIX"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_UNIX = "net.sourceforge.phpeclipse.ui.actions.ConvertLineDelimitersToUNIX"; //$NON-NLS-1$

  /**
   * Source menu: name of standardConvert Line Delimiters ToMac global action
   * (value <code>"org.phpeclipse.phpdt.ui.actions.ConvertLineDelimitersToMac"</code>).
   */
  public static String CONVERT_LINE_DELIMITERS_TO_MAC = "net.sourceforge.phpeclipse.ui.actions.ConvertLineDelimitersToMac"; //$NON-NLS-1$

  /**
   * Navigate menu: name of standard Open global action
   * (value <code>"org.phpeclipse.phpdt.ui.actions.Open"</code>).
   */
  public static final String OPEN = "net.sourceforge.phpeclipse.ui.actions.Open"; //$NON-NLS-1$

}
