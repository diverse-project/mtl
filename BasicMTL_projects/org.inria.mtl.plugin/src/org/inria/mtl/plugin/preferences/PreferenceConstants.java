/*
* $Id: PreferenceConstants.java,v 1.3 2004-05-19 09:21:29 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.preferences;


import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.inria.mtl.plugin.MTLPlugin;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.WorkbenchChainedTextFontFieldEditor;

/**
 * Preference constants used in the MTL-UI preference store. Clients should only read the
 * JDT-UI preference store using these values. Clients are not allowed to modify the 
 * preference store programmatically.
 * 
 * @since 2.0
  */
public class PreferenceConstants {

  private PreferenceConstants() {
  }

  public final static String MTL = "__mtl"; //$NON-NLS-1$
  public final static int MTL_FILE = 1; 

  /**
   * A named preference that controls return type rendering of methods in the UI.
   * <p>
   * Value is of type <code>Boolean</code>: if <code>true</code> return types
   * are rendered
   * </p>
   */
  public static final String APPEARANCE_METHOD_RETURNTYPE = "net.sourceforge.phpdt.ui.methodreturntype"; //$NON-NLS-1$
  
  
  public static  String JAVA_SRCNAME = "srcjavafolder"; //$NON-NLS-1$
  public static  IFolder FJAVA_SRCNAME ;
  public static  String MTL_SRCNAME = "srcmtlfolder"; //$NON-NLS-1$
  public static  IFolder FMTL_SRCNAME ; 
  public static  String JAVA_BINNAME = "binjavafolder"; //$NON-NLS-1$
  public static   IFolder FJAVA_BINNAME; 
  public static  String MTL_BINNAME = "binmtlfolder"; //$NON-NLS-1$
  public static  IFolder FMTL_BINNAME ;
  public static  String OUTPUT_BUILDNAME = "outputfolder"; //$NON-NLS-1$
  public static  IFolder FOUTPUT_BUILDNAME; 
  public static final String SHOW_OUTPUT_IN_CONSOLE = "_show_output_in_console";
  public static final String AUTO_COMPILE = "_auto_compile";
  
//  //Editor preferences
//  /**
//	* A named preference that holds the color used as the text foreground.
//	* <p>
//	* Value is of type <code>String</code>. A RGB color value encoded as a string
//	* using class <code>PreferenceConverter</code>
//	* </p>
//	* 
//	* @see org.eclipse.jface.resource.StringConverter
//	* @see org.eclipse.jface.preference.PreferenceConverter
//	*/
//   public final static String EDITOR_FOREGROUND_COLOR = AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND;
//
//   /**
//	* A named preference that describes if the system default foreground color
//	* is used as the text foreground.
//	* <p>
//	* Value is of type <code>Boolean</code>.
//	* </p>
//	*/
//   public final static String EDITOR_FOREGROUND_DEFAULT_COLOR = AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND_SYSTEM_DEFAULT;

  public final static String EDITOR_CORRECTION_INDICATION = "JavaEditor.ShowTemporaryProblem"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the editor shows problem indicators in text (squiggly lines). 
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_PROBLEM_INDICATION = "problemIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render problem indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
    */
  public final static String EDITOR_PROBLEM_INDICATION_COLOR = "problemIndicationColor"; //$NON-NLS-1$

  /**PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR;
   * A named preference that controls whether the editor shows warning indicators in text (squiggly lines). 
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_WARNING_INDICATION = "warningIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render warning indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
   * 
   * @see #EDITOR_WARNING_INDICATION
   * @see org.eclipse.jface.resource.StringConverter
   * @see org.eclipse.jface.preference.PreferenceConverter
   */
  public final static String EDITOR_WARNING_INDICATION_COLOR = "warningIndicationColor"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the editor shows task indicators in text (squiggly lines). 
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_TASK_INDICATION = "taskIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render task indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
   * 
   * @see #EDITOR_TASK_INDICATION
   * @see org.eclipse.jface.resource.StringConverter
   * @see org.eclipse.jface.preference.PreferenceConverter
   */
  public final static String EDITOR_TASK_INDICATION_COLOR = "taskIndicationColor"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the editor shows bookmark
   * indicators in text (squiggly lines).
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   * @since 2.1
   */
  public final static String EDITOR_BOOKMARK_INDICATION = "bookmarkIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render bookmark indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
   */
  public final static String EDITOR_BOOKMARK_INDICATION_COLOR = "bookmarkIndicationColor"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the editor shows search
   * indicators in text (squiggly lines).
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_SEARCH_RESULT_INDICATION = "searchResultIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render search indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
   */
  public final static String EDITOR_SEARCH_RESULT_INDICATION_COLOR = "searchResultIndicationColor"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the editor shows unknown
   * indicators in text (squiggly lines).
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_UNKNOWN_INDICATION = "othersIndication"; //$NON-NLS-1$

  /**
   * A named preference that holds the color used to render unknown
   * indicators.
   * <p>
   * Value is of type <code>String</code>. A RGB color value encoded as a string
   * using class <code>PreferenceConverter</code>
   * </p>
   */
  public final static String EDITOR_UNKNOWN_INDICATION_COLOR = "othersIndicationColor"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows error
   * indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_ERROR_INDICATION_IN_OVERVIEW_RULER = "errorIndicationInOverviewRuler"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows warning
   * indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_WARNING_INDICATION_IN_OVERVIEW_RULER = "warningIndicationInOverviewRuler"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows task
   * indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_TASK_INDICATION_IN_OVERVIEW_RULER = "taskIndicationInOverviewRuler"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows
   * bookmark indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_BOOKMARK_INDICATION_IN_OVERVIEW_RULER = "bookmarkIndicationInOverviewRuler"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows
   * search result indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER = "searchResultIndicationInOverviewRuler"; //$NON-NLS-1$

  /**
   * A named preference that controls whether the overview ruler shows
   * unknown indicators.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public final static String EDITOR_UNKNOWN_INDICATION_IN_OVERVIEW_RULER = "othersIndicationInOverviewRuler"; //$NON-NLS-1$
  
  /**
	 * A named preference that holds the color used as the text foreground.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_FOREGROUND_COLOR = AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND;

	/**
	 * A named preference that describes if the system default foreground color
	 * is used as the text foreground.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_FOREGROUND_DEFAULT_COLOR = AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND_SYSTEM_DEFAULT;

	/**
	 * A named preference that holds the color used as the text background.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_BACKGROUND_COLOR = AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND;

	/**
	 * A named preference that describes if the system default background color
	 * is used as the text foreground.
	 * <p>
	 * Value is of type <code>Boolean</code>. 
	 * </p>
	 */
	public final static String EDITOR_BACKGROUND_DEFAULT_COLOR = AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND_SYSTEM_DEFAULT;

	/**
	 * Preference key suffix for bold text style preference keys.
	 */
	public static final String EDITOR_BOLD_SUFFIX = "_bold"; //$NON-NLS-1$
	
	public static final String MTL_MULTILINE_COMMENT = "_mtl_multilineComment";

	/**
	 * A named preference that holds the color used to render multi line comments.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_MULTI_LINE_COMMENT_COLOR = "_mtl_multilineComment";

	/**
	 * A named preference that controls whether multi line comments are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> multi line comments are rendered
	 * in bold. If <code>false</code> the are rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_MULTI_LINE_COMMENT_BOLD = MTL_MULTILINE_COMMENT + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render single line comments.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_SINGLELINE_COMMENT = "_mtl_singlelineComment";
	public final static String EDITOR_SINGLE_LINE_COMMENT_COLOR = MTL_SINGLELINE_COMMENT;

	/**
	 * A named preference that controls whether sinle line comments are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered
	 * in bold. If <code>false</code> the are rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_BOLD = MTL_SINGLELINE_COMMENT + EDITOR_BOLD_SUFFIX;

	  /**
		   * A named preference that holds the color used to render php start and stop tags.
		   * <p>
		   * Value is of type <code>String</code>. A RGB color value encoded as a string
		   * using class <code>PreferenceConverter</code>
		   * </p>
		   * 
		   * @see org.eclipse.jface.resource.StringConverter
		   * @see org.eclipse.jface.preference.PreferenceConverter
		   */
	public static final String MTL_TAG = "_mtl_tag";
		  public final static String EDITOR_MTL_TAG_COLOR = MTL_TAG;

		  /**
		   * A named preference that controls whether php start and stop tags are rendered in bold.
		   * <p>
		   * Value is of type <code>Boolean</code>.
		   * </p>
		   */
		  public final static String EDITOR_MTL_TAG_BOLD = MTL_TAG + EDITOR_BOLD_SUFFIX;


	/**
	 * A named preference that holds the color used to render php keywords.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_KEYWORD = "_mtl_keyword";
	public final static String EDITOR_MTL_KEYWORD_COLOR = MTL_KEYWORD;

	/**
	 * A named preference that controls whether keywords are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_MTL_KEYWORD_BOLD = MTL_KEYWORD + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render predefined mtl
	 * function names.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_FUNCTIONNAME = "_mtl_functionname";
	public final static String EDITOR_MTL_FUNCTIONNAME_COLOR = MTL_FUNCTIONNAME;

	/**
	 * A named preference that controls whether function names are rendered in
	 * bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_MTL_FUNCTIONNAME_BOLD = MTL_FUNCTIONNAME + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render php
	 * variables.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_VARIABLE = "_mtl_variable";
	public final static String EDITOR_MTL_VARIABLE_COLOR = MTL_VARIABLE;

	/**
	 * A named preference that controls whether variables are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_MTL_VARIABLE_BOLD = MTL_VARIABLE + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render php constants.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_CONSTANT = "_mtl_constant";
	public final static String EDITOR_MTL_CONSTANT_COLOR = MTL_CONSTANT;

	/**
	 * A named preference that controls whether constants are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	
	public final static String EDITOR_MTL_CONSTANT_BOLD = MTL_CONSTANT + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render php types.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_TYPE = "_mtl_type";
	public final static String EDITOR_MTL_TYPE_COLOR = MTL_TYPE;

	/**
	 * A named preference that controls whether types are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_MTL_TYPE_BOLD = MTL_TYPE + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render string constants.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_STRING = "_mtl_string";
	public final static String EDITOR_STRING_COLOR = MTL_STRING;

	/**
	 * A named preference that controls whether string constants are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_STRING_BOLD = MTL_STRING + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that holds the color used to render php default text.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public static final String MTL_DEFAULT = "_mtl_default";
	public final static String EDITOR_MTL_DEFAULT_COLOR = MTL_DEFAULT;

	/**
	 * A named preference that controls whether Java default text is rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_MTL_DEFAULT_BOLD = MTL_DEFAULT + EDITOR_BOLD_SUFFIX;
	/**
	   * A name preference that holds the auto activation delay time in milli seconds.
	   * <p>
	   * Value is of type <code>Int</code>.
	   * </p>
	   */
	  public final static String CODEASSIST_AUTOACTIVATION = "content_assist_autoactivation"; //$NON-NLS-1$
	  public final static String CODEASSIST_AUTOACTIVATION_DELAY = "content_assist_autoactivation_delay"; //$NON-NLS-1$

	  /**
	   * A named preference that controls if code assist contains only visible proposals.
	   * <p>
	   * Value is of type <code>Boolean</code>. if <code>true<code> code assist only contains visible members. If 
	   * <code>false</code> all members are included.
	   * </p>
	   */
	  public final static String CODEASSIST_SHOW_VISIBLE_PROPOSALS = "content_assist_show_visible_proposals"; //$NON-NLS-1$

	  /**
	   * A named preference that controls if the Java code assist inserts a
	   * proposal automatically if only one proposal is available.
	   * <p>
	   * Value is of type <code>Boolean</code>.
	   * </p>
	   * @since 2.1
	   */
	  public final static String CODEASSIST_AUTOINSERT = "content_assist_autoinsert"; //$NON-NLS-1$

	  /**
	   * A named preference that controls if the Java code assist adds import
	   * statements.
	   * <p>
	   * Value is of type <code>Boolean</code>.
	   * </p>
	   * @since 2.1
	   */

	/**
	   * A named preference that holds the background color used in the code assist selection dialog.
	   * <p>
	   * Value is of type <code>String</code>. A RGB color value encoded as a string
	   * using class <code>PreferenceConverter</code>
	   * </p>
	   * 
	   * @see org.eclipse.jface.resource.StringConverter
	   * @see org.eclipse.jface.preference.PreferenceConverter
	   */
	  public final static String CODEASSIST_PROPOSALS_BACKGROUND = "content_assist_proposals_background"; //$NON-NLS-1$

	  /**
	   * A named preference that holds the foreground color used in the code assist selection dialog.
	   * <p>
	   * Value is of type <code>String</code>. A RGB color value encoded as a string
	   * using class <code>PreferenceConverter</code>
	   * </p>
	   * 
	   * @see org.eclipse.jface.resource.StringConverter
	   * @see org.eclipse.jface.preference.PreferenceConverter
	   */
	  public final static String CODEASSIST_PROPOSALS_FOREGROUND = "content_assist_proposals_foreground"; //$NON-NLS-1$
	  
	/** Preference key for content assist proposal color */
	 public final static String PROPOSALS_FOREGROUND = "content_assist_proposals_foreground"; //$NON-NLS-1$
	 /** Preference key for content assist proposal color */
	 public final static String PROPOSALS_BACKGROUND = "content_assist_proposals_background"; //$NON-NLS-1$
	 
	/**
	 * A named preference that holds the number of spaces used per tab in the editor.
	 * <p>
	 * Value is of type <code>Int</code>: positive int value specifying the number of
	 * spaces per tab.
	 * </p>
	 */
	public final static String EDITOR_TAB_WIDTH = "net.sourceforge.phpdt.ui.editor.tab.width"; //$NON-NLS-1$


	 
	public final static String EDITOR_PRINT_MARGIN = "printMargin"; //$NON-NLS-
	
	/**
	 * Print margin column. Int value.
	 */
	public final static String EDITOR_PRINT_MARGIN_COLUMN = "printMarginColumn"; //$NON-NLS-1$
	
	public final static String MTL_OUTLINE_CLASS = "mtloutlineclass"; //$NON-NLS-1$
	
	public final static String MTL_OUTLINE_LIBRARY = "mtloutlinelibrary"; //$NON-NLS-1$
	
	public final static String MTL_OUTLINE_VAR = "mtloutlinevar"; //$NON-NLS-1$
	
	public final static String MTL_OUTLINE_FUNC = "mtloutlinefunc"; //$NON-NLS-1$
	
	/**
		 * Pop-up menu: name of group for code generation actions (
		 * value <code>"group.generate"</code>).
		 */	
	public static final String GROUP_GENERATE=	"group.generate"; //$NON-NLS-1$

	/**
	 * Pop-up menu: name of group for source actions. This is an alias for
	 * <code>GROUP_GENERATE</code> to be more consistent with main menu
	 * bar structure.
	 * 
	 */	
	public static final String GROUP_SOURCE=	GROUP_GENERATE;

	/**
		 * Pop-up menu: name of group for source actions. This is an alias for
		 * <code>GROUP_GENERATE</code> to be more consistent with main menu
		 * bar structure.
		 * 
		 */	
		public static final String GROUP_ADDITIONS=	"group.additions";
		
	/**
	 * A named preference that holds the color used for 'linked-mode' underline.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string
	 * using class <code>PreferenceConverter</code>
	 * </p>
	 *
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 * @since 2.1
	 */
	public final static String EDITOR_LINK_COLOR = "linkColor"; //$NON-NLS-1$
	/**
	   * A named preference that controls if the line number ruler is shown in the UI.
	   * <p>
	   * Value is of type <code>Boolean</code>.
	   * </p>
	   */
	  public final static String EDITOR_LINE_NUMBER_RULER = "lineNumberRuler"; //$NON-NLS-1$

	  /**
	   * A named preference that holds the color used to render line numbers inside the line number ruler.
	   * <p>
	   * Value is of type <code>String</code>. A RGB color value encoded as a string
	   * using class <code>PreferenceConverter</code>
	   * </p>
	   * 
	   * @see org.eclipse.jface.resource.StringConverter
	   * @see org.eclipse.jface.preference.PreferenceConverter
	   * @see #EDITOR_LINE_NUMBER_RULER
	   */
	  public final static String EDITOR_LINE_NUMBER_RULER_COLOR = "lineNumberColor"; //$NON-NLS-1$

	/**
		 * Pop-up menu: name of group for open actions (value <code>"group.open"</code>).
		 * <p>
		 * Examples for open actions are:
		 * <ul>
		 *  <li>Open To</li>
		 *  <li>Open With</li>
		 * </ul>
		 * </p>
		 */
		public static final String GROUP_OPEN=		"group.open"; //$NON-NLS-1$
	
		/**
		 * Pop-up menu: name of group for show actions (value <code>"group.show"</code>).
		 * <p>
		 * Examples for show actions are:
		 * <ul>
		 *  <li>Show in Navigator</li>
		 *  <li>Show in Type Hierarchy</li>
		 * </ul>
		 * </p>
		 */
		public static final String GROUP_SHOW=		"group.show"; //$NON-NLS-1$
	
		/**
		 * Pop-up menu: name of group for new actions (value <code>"group.new"</code>).
		 * <p>
		 * Examples for new actions are:
		 * <ul>
		 *  <li>Create new class</li>
		 *  <li>Create new interface</li>
		 * </ul>
		 * </p>
	
 /**
   * A named preference that controls whether hover tooltips in the editor are turned on or off.
   * <p>
   * Value is of type <code>Boolean</code>.
   * </p>
   */
  public static final String EDITOR_SHOW_HOVER = "net.sourceforge.phpdt.ui.editor.showHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when no control key is
   * pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a hover
   * contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI
   * @since 2.1
   */
  public static final String EDITOR_NONE_HOVER = "noneHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>CTRL</code> modifier key is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI
   * @since 2.1
   */
  public static final String EDITOR_CTRL_HOVER = "ctrlHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>SHIFT</code> modifier key is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI ID_*_HOVER
   * @since 2.1
   */
  public static final String EDITOR_SHIFT_HOVER = "shiftHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>CTRL + ALT</code> modifier keys is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI ID_*_HOVER
   * @since 2.1
   */
  public static final String EDITOR_CTRL_ALT_HOVER = "ctrlAltHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>CTRL + ALT + SHIFT</code> modifier keys is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI ID_*_HOVER
   * @since 2.1
   */
  public static final String EDITOR_CTRL_ALT_SHIFT_HOVER = "ctrlAltShiftHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>CTRL + SHIFT</code> modifier keys is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code> or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI ID_*_HOVER
   * @since 2.1
   */
  public static final String EDITOR_CTRL_SHIFT_HOVER = "ctrlShiftHover"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover shown when the
   * <code>ALT</code> modifier key is pressed.
   * <p>Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code>,
   * <code>EDITOR_DEFAULT_HOVER_CONFIGURED_ID</code>  or the hover id of a
   * hover contributed as <code>phpEditorTextHovers</code>.
   * </p>
   * @see #EDITOR_NO_HOVER_CONFIGURED_ID
   * @see #EDITOR_DEFAULT_HOVER_CONFIGURED_ID
   * @see JavaUI ID_*_HOVER
   * @since 2.1
   */
  public static final String EDITOR_ALT_SHIFT_HOVER = "altShiftHover"; //$NON-NLS-1$

  /**
   * A string value used by the named preferences for hover configuration to
   * descibe that no hover should be shown for the given key modifiers.
   * @since 2.1
   */
  public static final String EDITOR_NO_HOVER_CONFIGURED_ID = "noHoverConfiguredId"; //$NON-NLS-1$

  /**
   * A string value used by the named preferences for hover configuration to
   * descibe that the default hover should be shown for the given key
   * modifiers. The default hover is described by the
   * <code>EDITOR_DEFAULT_HOVER</code> property.
   * @since 2.1
   */
  public static final String EDITOR_DEFAULT_HOVER_CONFIGURED_ID = "defaultHoverConfiguredId"; //$NON-NLS-1$

  /**
   * A named preference that defines the hover named the 'default hover'.
   * Value is of type <code>String</code>: possible values are <code>
   * EDITOR_NO_HOVER_CONFIGURED_ID</code> or <code> the hover id of a hover
   * contributed as <code>phpEditorTextHovers</code>.
   * </p>
   *@since 2.1
   */
  public static final String EDITOR_DEFAULT_HOVER = "defaultHover"; //$NON-NLS-1$

  /**
	 * A named preference that controls if the overview ruler is shown in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	public final static String EDITOR_OVERVIEW_RULER = "overviewRuler"; //$NON-NLS-1$
	
	public static final String MTL_USERDEF_XMLFILE = "_userdef_xmlfile";
	
	/**
	  * A named preference that specifies if the editor uses spaces for tabs.
	  * <p>
	  * Value is of type <code>Boolean</code>. If <code>true</code>spaces instead of tabs are used
	  * in the editor. If <code>false</code> the editor inserts a tab character when pressing the tab
	  * key.
	  * </p>
	  */
	 public final static String EDITOR_SPACES_FOR_TABS = "spacesForTabs"; //$NON-NLS-1$
	 
	/**
	   * A named preference that holds the color used to render linked positions inside code templates.
	   * <p>
	   * Value is of type <code>String</code>. A RGB color value encoded as a string
	   * using class <code>PreferenceConverter</code>
	   * </p>
	   * 
	   * @see org.eclipse.jface.resource.StringConverter
	   * @see org.eclipse.jface.preference.PreferenceConverter
	   */
	  public final static String EDITOR_LINKED_POSITION_COLOR = "linkedPositionColor"; //$NON-NLS-1$

	/**
		 * A named preference that controls whether the current line highlighting is turned on or off.
		 * <p>
		 * Value is of type <code>Boolean</code>.
		 * </p>
		 */
		public final static String EDITOR_CURRENT_LINE= "currentLine"; //$NON-NLS-1$

		/**
		 * A named preference that holds the color used to highlight the current line.
		 * <p>
		 * Value is of type <code>String</code>. A RGB color value encoded as a string
		 * using class <code>PreferenceConverter</code>
		 * </p>
		 * 
		 * @see org.eclipse.jface.resource.StringConverter
		 * @see org.eclipse.jface.preference.PreferenceConverter
		 */
		public final static String EDITOR_CURRENT_LINE_COLOR= "currentLineColor"; //$NON-NLS-1$
		
	/**
		 * A named preference that controls whether bracket matching highlighting is turned on or off.
		 * <p>
		 * Value is of type <code>Boolean</code>.
		 * </p>
		 */
		public final static String EDITOR_MATCHING_BRACKETS= "matchingBrackets"; //$NON-NLS-1$

		/**
		 * A named preference that holds the color used to highlight matching brackets.
		 * <p>
		 * Value is of type <code>String</code>. A RGB color value encoded as a string 
		 * using class <code>PreferenceConverter</code>
		 * </p>
		 * 
		 * @see org.eclipse.jface.resource.StringConverter
		 * @see org.eclipse.jface.preference.PreferenceConverter
		 */
		public final static String EDITOR_MATCHING_BRACKETS_COLOR=  "matchingBracketsColor"; //$NON-NLS-1$


	/**
	 * A named preference that controls whether the 'close strings' feature
	 *  is   enabled in MTL mode
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * @since 2.1
	 */
	public final static String EDITOR_CLOSE_STRINGS_MTL = "closeStringsMTL"; //$NON-NLS-1$

  /**
	 * A named preference that controls whether the 'close brackets' feature is
	 * enabled in MTL mode
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * @since 2.1
	 */
	public final static String EDITOR_CLOSE_BRACKETS_MTL = "closeBracketsMTL"; //$NON-NLS-1$

   /**
	 * A named preference that controls whether the 'smart paste' feature is
	 * enabled.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * @since 2.1
	 */
	public final static String EDITOR_SMART_PASTE = "smartPaste"; //$NON-NLS-1$



	 

  /**
   * A named preference that controls if override indicators are rendered in the UI.
   * <p>
   * Value is of type <code>Boolean</code>: if <code>true</code> override 
   * indicators are rendered
   * </p>
	 */
    /**
   * A named preference that controls if empty inner packages are folded in
   * the hierarchical mode of the package explorer.
   * <p>
   * Value is of type <code>Boolean</code>: if <code>true</code> empty
   * inner packages are folded.
   * </p>
   * @since 2.1
   */
  public static String MTL_COMPILER_PATH="mtlcompilerpath" ; //$NON-NLS-1$


  public static void initializeDefaultValues(IPreferenceStore store) {
    //MTL Compiler Page
	store.setDefault(PreferenceConstants.MTL_COMPILER_PATH, " ");
	
	//MTL Folder settings
	store.setDefault(PreferenceConstants.JAVA_BINNAME,"bin");
	store.setDefault(PreferenceConstants.JAVA_SRCNAME,"src");
	store.setDefault(PreferenceConstants.MTL_BINNAME,"tll");
	store.setDefault(PreferenceConstants.MTL_SRCNAME,"src");
	store.setDefault(PreferenceConstants.OUTPUT_BUILDNAME,"build");

	final RGB[] rgbs = new RGB[3];
	final Display display = Display.getDefault();
	display.syncExec(new Runnable() {
	  public void run() {
		Color c = display.getSystemColor(SWT.COLOR_GRAY);
		rgbs[0] = c.getRGB();
		c = display.getSystemColor(SWT.COLOR_LIST_FOREGROUND);
		rgbs[1] = c.getRGB();
		c = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
		rgbs[2] = c.getRGB();
	  }
	});

	   store.setDefault(PreferenceConstants.EDITOR_PRINT_MARGIN, false);
	   store.setDefault(PreferenceConstants.EDITOR_PRINT_MARGIN_COLUMN, 80);
	   //PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_PRINT_MARGIN_COLOR, new RGB(176, 180, 185));

	   store.setDefault(PreferenceConstants.EDITOR_PROBLEM_INDICATION, true);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR, new RGB(255, 0, 128));
	   store.setDefault(PreferenceConstants.EDITOR_ERROR_INDICATION_IN_OVERVIEW_RULER, true);

	   store.setDefault(PreferenceConstants.EDITOR_WARNING_INDICATION, true);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_WARNING_INDICATION_COLOR, new RGB(244, 200, 45));
	   store.setDefault(PreferenceConstants.EDITOR_WARNING_INDICATION_IN_OVERVIEW_RULER, true);

	   store.setDefault(PreferenceConstants.EDITOR_TASK_INDICATION, false);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_TASK_INDICATION_COLOR, new RGB(0, 128, 255));
	   store.setDefault(PreferenceConstants.EDITOR_TASK_INDICATION_IN_OVERVIEW_RULER, false);

	   store.setDefault(PreferenceConstants.EDITOR_BOOKMARK_INDICATION, false);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_BOOKMARK_INDICATION_COLOR, new RGB(34, 164, 99));
	   store.setDefault(PreferenceConstants.EDITOR_BOOKMARK_INDICATION_IN_OVERVIEW_RULER, false);

	   store.setDefault(PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION, false);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION_COLOR, new RGB(192, 192, 192));
	   store.setDefault(PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER, false);

	   store.setDefault(PreferenceConstants.EDITOR_UNKNOWN_INDICATION, false);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_UNKNOWN_INDICATION_COLOR, new RGB(0, 0, 0));
	   store.setDefault(PreferenceConstants.EDITOR_UNKNOWN_INDICATION_IN_OVERVIEW_RULER, false);

	   store.setDefault(PreferenceConstants.EDITOR_CORRECTION_INDICATION, true);
//	   store.setDefault(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, false);

//	   store.setDefault(PreferenceConstants.EDITOR_EVALUTE_TEMPORARY_PROBLEMS, true);

//	   store.setDefault(PreferenceConstants.EDITOR_OVERVIEW_RULER, true);

	   store.setDefault(PreferenceConstants.EDITOR_LINE_NUMBER_RULER, false);
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_LINE_NUMBER_RULER_COLOR, new RGB(0, 0, 0));

	   WorkbenchChainedTextFontFieldEditor.startPropagate(store, JFaceResources.TEXT_FONT);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_LINKED_POSITION_COLOR, new RGB(0, 200, 100));
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_LINK_COLOR, new RGB(0, 0, 255));

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_FOREGROUND_COLOR, rgbs[1]);
	   store.setDefault(PreferenceConstants.EDITOR_FOREGROUND_DEFAULT_COLOR, true);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_BACKGROUND_COLOR, rgbs[2]);
	   store.setDefault(PreferenceConstants.EDITOR_BACKGROUND_DEFAULT_COLOR, true);

	   store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 4);
	   store.setDefault(PreferenceConstants.EDITOR_SPACES_FOR_TABS, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR, new RGB(63, 127, 95));
	   store.setDefault(PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_BOLD, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, new RGB(63, 127, 95));
	   store.setDefault(PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_BOLD, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_TAG_COLOR, new RGB(255, 0, 128));
       store.setDefault(PreferenceConstants.EDITOR_MTL_TAG_BOLD, true);
				
	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_KEYWORD_COLOR, new RGB(127, 0, 85));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_KEYWORD_BOLD, true);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_FUNCTIONNAME_COLOR, new RGB(127, 127, 159));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_FUNCTIONNAME_BOLD, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_VARIABLE_COLOR, new RGB(127, 159, 191));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_VARIABLE_BOLD, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_CONSTANT_COLOR, new RGB(127, 0, 85));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_CONSTANT_BOLD, false);
	   
	  store.setDefault(PreferenceConstants.EDITOR_SMART_PASTE, true);
	   store.setDefault(PreferenceConstants.EDITOR_CLOSE_STRINGS_MTL, true);
	   store.setDefault(PreferenceConstants.EDITOR_CLOSE_BRACKETS_MTL, true);
	    

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_TYPE_COLOR, new RGB(127, 0, 85));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_TYPE_BOLD, false);

	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_STRING_COLOR, new RGB(42, 0, 255));
	   store.setDefault(PreferenceConstants.EDITOR_STRING_BOLD, false);
	   
	store.setDefault(PreferenceConstants.EDITOR_CURRENT_LINE, true);
		PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_CURRENT_LINE_COLOR, new RGB(225, 235, 224));


	   PreferenceConverter.setDefault(store, PreferenceConstants.EDITOR_MTL_DEFAULT_COLOR, new RGB(0, 0, 0));
	   store.setDefault(PreferenceConstants.EDITOR_MTL_DEFAULT_BOLD, false);

	   store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION, true);
	   store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY, 500);
	   
		store.setDefault(PreferenceConstants.EDITOR_OVERVIEW_RULER, true);

	   store.setDefault(PreferenceConstants.CODEASSIST_AUTOINSERT, true);
	   store.setDefault(PreferenceConstants.MTL_OUTLINE_LIBRARY, true);
	   store.setDefault(PreferenceConstants.MTL_OUTLINE_VAR, true);
	   store.setDefault(PreferenceConstants.MTL_OUTLINE_FUNC, true);
	   store.setDefault(PreferenceConstants.MTL_OUTLINE_CLASS, true);
	   PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PROPOSALS_BACKGROUND, new RGB(254, 241, 233));
	   PreferenceConverter.setDefault(store, PreferenceConstants.CODEASSIST_PROPOSALS_FOREGROUND, new RGB(0, 0, 0));
	   
	//	store.setDefault(PreferenceConstants.EDITOR_DEFAULT_HOVER, JavaPlugin.ID_BESTMATCH_HOVER);
	  store.setDefault(PreferenceConstants.EDITOR_NONE_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);
	  //		store.setDefault(PreferenceConstants.EDITOR_CTRL_HOVER, JavaPlugin.ID_SOURCE_HOVER);
	  store.setDefault(PreferenceConstants.EDITOR_SHIFT_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);
	  store.setDefault(PreferenceConstants.EDITOR_CTRL_SHIFT_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);
	  store.setDefault(PreferenceConstants.EDITOR_CTRL_ALT_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);
	  store.setDefault(PreferenceConstants.EDITOR_ALT_SHIFT_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);
	  store.setDefault(PreferenceConstants.EDITOR_CTRL_ALT_SHIFT_HOVER, PreferenceConstants.EDITOR_DEFAULT_HOVER_CONFIGURED_ID);

	
  }

  /**
   * Returns the JDT-UI preference store.
   * 
   * @return the JDT-UI preference store
   */
  public static IPreferenceStore getPreferenceStore() {
	return MTLPlugin.getDefault().getPreferenceStore();
  }

	
}
