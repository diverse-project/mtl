/*
* $Id: ContentAssistPreferences.java,v 1.2 2004-08-26 12:40:32 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion;

import org.inria.mtl.preferences.PreferencesConstants;
import org.inria.mtl.editors.utils.IColorManager;
import org.inria.mtl.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.editors.completion.MTLCompletionProcessor; 

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB; 

public class ContentAssistPreferences {

  /** Preference key for content assist auto activation */
  private final static String AUTOACTIVATION = PreferencesConstants.CODEASSIST_AUTOACTIVATION;
  /** Preference key for content assist auto activation delay */
  private final static String AUTOACTIVATION_DELAY = PreferencesConstants.CODEASSIST_AUTOACTIVATION_DELAY;
  /** Preference key for content assist proposal color */
  private final static String PROPOSALS_FOREGROUND = PreferencesConstants.CODEASSIST_PROPOSALS_FOREGROUND;
  /** Preference key for content assist proposal color */
  private final static String PROPOSALS_BACKGROUND = PreferencesConstants.CODEASSIST_PROPOSALS_BACKGROUND;
  /** Preference key for content assist parameters color */
  private final static String PARAMETERS_FOREGROUND = PROPOSALS_FOREGROUND; 
  /** Preference key for content assist parameters color */
  private final static String PARAMETERS_BACKGROUND = PROPOSALS_BACKGROUND;
  /** Preference key for content assist completion replacement color */
  private final static String COMPLETION_REPLACEMENT_FOREGROUND = PROPOSALS_FOREGROUND;
  /** Preference key for content assist completion replacement color */
  private final static String COMPLETION_REPLACEMENT_BACKGROUND = PROPOSALS_BACKGROUND;
  /** Preference key for content assist auto insert */
  private final static String AUTOINSERT = PreferencesConstants.CODEASSIST_AUTOINSERT;
 
  /** Preference key for php content assist auto activation triggers */
  private final static String AUTOACTIVATION_TRIGGERS_JAVA = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS_JAVA;
   /** Preference key for visibility of proposals */
  private final static String SHOW_VISIBLE_PROPOSALS = PreferencesConstants.CODEASSIST_SHOW_VISIBLE_PROPOSALS;
  /** Preference key for alphabetic ordering of proposals */
  private final static String ORDER_PROPOSALS = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_ORDER_PROPOSALS;
  /** Preference key for case sensitivity of propsals */
  private final static String CASE_SENSITIVITY = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_CASE_SENSITIVITY;
  /** Preference key for adding imports on code assist */
  private final static String ADD_IMPORT = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_ADDIMPORT;
  /** Preference key for inserting content assist */
  private static final String INSERT_COMPLETION = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_INSERT_COMPLETION;
  /** Preference key for filling argument names on method completion */
  private static final String FILL_METHOD_ARGUMENTS = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES;
  /** Preference key for guessing argument names on method completion */
  private static final String GUESS_METHOD_ARGUMENTS = org.eclipse.jdt.ui.PreferenceConstants.CODEASSIST_GUESS_METHOD_ARGUMENTS;

  private static Color getColor(IPreferenceStore store, String key, IColorManager manager) {
	RGB rgb = PreferenceConverter.getColor(store, key);
	return manager.getColor(rgb);
  }

  private static Color getColor(IPreferenceStore store, String key) {
	MTLEditorEnvironment textTools = MTLPlugin.getDefault().getMTLEditorEnvironment();
	return getColor(store, key, textTools.getColorManager());
  }

  private static MTLCompletionProcessor getMTLProcessor(ContentAssistant assistant) {
	IContentAssistProcessor p = assistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
	if (p instanceof MTLCompletionProcessor) 
		  return (MTLCompletionProcessor) p;
	
	return null;
  }


  private static void configureMTLProcessor(ContentAssistant assistant, IPreferenceStore store) {
	MTLCompletionProcessor pcp = getMTLProcessor(assistant);
	if (pcp == null)
		 return;
	
	String triggers = store.getString(AUTOACTIVATION_TRIGGERS_JAVA);
	if (triggers != null)
	  pcp.setCompletionProposalAutoActivationCharacters(triggers.toCharArray());
	boolean enabled;
	enabled = store.getBoolean(ORDER_PROPOSALS);
	pcp.orderProposalsAlphabetically(enabled);
  }

    /**
   * Configure the given content assistant from the given store.
   */
  public static void configure(ContentAssistant assistant, IPreferenceStore store) {
	
	MTLEditorEnvironment textTools = MTLPlugin.getDefault().getMTLEditorEnvironment();
	IColorManager manager = textTools.getColorManager();

	boolean enabled = store.getBoolean(AUTOACTIVATION);
	assistant.enableAutoActivation(enabled);
	
	int delay = store.getInt(AUTOACTIVATION_DELAY);
	assistant.setAutoActivationDelay(delay);
	
	Color c = getColor(store, PROPOSALS_FOREGROUND, manager);
	assistant.setProposalSelectorForeground(c);
	
	c = getColor(store, PROPOSALS_BACKGROUND, manager);
	assistant.setProposalSelectorBackground(c);
	
	c = getColor(store, PARAMETERS_FOREGROUND, manager);
	assistant.setContextInformationPopupForeground(c);
	assistant.setContextSelectorForeground(c);

	c = getColor(store, PARAMETERS_BACKGROUND, manager);
	assistant.setContextInformationPopupBackground(c);
	assistant.setContextSelectorBackground(c);

	enabled = store.getBoolean(AUTOINSERT);
	assistant.enableAutoInsert(enabled);
	

	configureMTLProcessor(assistant, store); 
	
  }

  private static void changeMTLProcessor(ContentAssistant assistant, IPreferenceStore store, String key) {
	MTLCompletionProcessor jcp = getMTLProcessor(assistant);
	if (jcp == null)
	  return;

	if (AUTOACTIVATION_TRIGGERS_JAVA.equals(key)) {
	  String triggers = store.getString(AUTOACTIVATION_TRIGGERS_JAVA);
	  if (triggers != null)
		jcp.setCompletionProposalAutoActivationCharacters(triggers.toCharArray());
	}
	else if (ORDER_PROPOSALS.equals(key)) {
	  boolean enable = store.getBoolean(ORDER_PROPOSALS);
	  jcp.orderProposalsAlphabetically(enable);
	
	}
  }

  
  /**
   * Changes the configuration of the given content assistant according to the given property
   * change event and the given preference store.
   */
  public static void changeConfiguration(ContentAssistant assistant, IPreferenceStore store, PropertyChangeEvent event) {

	String p = event.getProperty();

	if (AUTOACTIVATION.equals(p)) {
	  boolean enabled = store.getBoolean(AUTOACTIVATION);
	  assistant.enableAutoActivation(enabled);
	} else if (AUTOACTIVATION_DELAY.equals(p)) {
	  int delay = store.getInt(AUTOACTIVATION_DELAY);
	  assistant.setAutoActivationDelay(delay);
	} else if (PROPOSALS_FOREGROUND.equals(p)) {
	  Color c = getColor(store, PROPOSALS_FOREGROUND);
	  assistant.setProposalSelectorForeground(c);
	} else if (PROPOSALS_BACKGROUND.equals(p)) {
	  Color c = getColor(store, PROPOSALS_BACKGROUND);
	  assistant.setProposalSelectorBackground(c);
	} else if (PARAMETERS_FOREGROUND.equals(p)) {
	  Color c = getColor(store, PARAMETERS_FOREGROUND);
	  assistant.setContextInformationPopupForeground(c);
	  assistant.setContextSelectorForeground(c);
	} else if (PARAMETERS_BACKGROUND.equals(p)) {
	  Color c = getColor(store, PARAMETERS_BACKGROUND);
	  assistant.setContextInformationPopupBackground(c);
	  assistant.setContextSelectorBackground(c);
	} else if (AUTOINSERT.equals(p)) {
	  boolean enabled = store.getBoolean(AUTOINSERT);
	  assistant.enableAutoInsert(enabled);
	}

	changeMTLProcessor(assistant, store, p);
	
  }

  public static boolean fillArgumentsOnMethodCompletion(IPreferenceStore store) {
	return store.getBoolean(FILL_METHOD_ARGUMENTS);
  }
}
