/*
* $Id: MTLEditorActionDefinitionIds.java,v 1.3 2004-05-19 09:21:02 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.actions;

/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface MTLEditorActionDefinitionIds {
	  /**
	   * Action definition ID of the edit -> go to matching bracket action
	   * (value <code>"org.phpeclipse.phpdt.ui.edit.text.php.goto.matching.bracket"</code>).
	   *
	   * @since 2.1
	   */
	  public static final String GOTO_MATCHING_BRACKET = "org.inria.mtl.plugin.editors.matching.bracket"; //$NON-NLS-1$

	  /**
	   * Value: net.sourceforge.phpeclipse.phpeditor.comment
	   */
	  public static final String COMMENT = "org.inria.mtl.plugin.editors.comment";

	  /**
	   * Value: org.inria.mtl.plugin.editors.uncomment
	   */
	  public static final String UNCOMMENT = "org.inria.mtl.plugin.editors.uncomment";

	  /**
	   * Action definition ID of the source -> format action 
	   */
	  public static final String FORMAT = "org.inria.mtl.plugin.editors.format"; //$NON-NLS-1$

	  /**
	  * Action definition ID of the edit -> content assist proposal action (value
	  * <code>"org.phpeclipse.phpdt.ui.edit.text.php.content.assist. proposals"
	  * </code>).
		 */
	public static final String CONTENT_ASSIST_PROPOSALS = "org.eclipse.ui.edit.text.contentAssist.proposals"; //$NON-NLS-1$
  
	  /**
		 * Action definition ID of the edit -> show Javadoc action
		 * (value <code>"org.eclipse.jdt.ui.edit.text.java.show.javadoc"</code>).
		 */
	  public static final String SHOW_JAVADOC = "org.inria.mtl.plugin.editors.ui.edit.text.java.show.javadoc"; //$NON-NLS-1$

}


