/*
* $Id: MTLEditorEnvironment.java,v 1.1 2004-07-30 14:08:18 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;


import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.DefaultPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.inria.mtl.editors.MTLCodeScanner;
import org.inria.mtl.editors.SingleTokenScanner;
import org.inria.mtl.preferences.PreferencesConstants;


/** 
 * Tools required to configure a MTL text viewer.
 * The color manager and all scanner exist only one time ie the same
 * instance are returned to all clients. Thus, clients share tools.
 * <p>
 * The MTLEditorEnvironment maintains singletons used by the MTL editor
 */
public class MTLEditorEnvironment {
	
	private class PreferenceListener implements IPropertyChangeListener, Preferences.IPropertyChangeListener {
		public void propertyChange(PropertyChangeEvent event) {
			adaptToPreferenceChange(event);
		}
		public void propertyChange(Preferences.PropertyChangeEvent event) {
					adaptToPreferenceChange(new PropertyChangeEvent(event.getSource(), event.getProperty(), event.getOldValue(), event.getNewValue()));
				}

	};
	
	/** The color manager */
	private MTLColorManager fColorManager;
	/** The MTL source code scanner */
	private MTLCodeScanner fCodeScanner;
	/** The MTL multiline comment scanner */
	private SingleTokenScanner fMultilineCommentScanner;
	/** The MTL singleline comment scanner */
	private SingleTokenScanner fSinglelineCommentScanner;
	/** The MTL string scanner */
	private SingleTokenScanner fStringScanner;
	/** The JavaDoc scanner */
//	private JavaDocScanner fJavaDocScanner;
	/** The MTL partitions scanner */
	private FastMTLPartitionScanner fPartitionScanner;	
	
	/** The preference store */
	private IPreferenceStore fPreferenceStore;
	/** The preference change listener */
	private PreferenceListener fPreferenceListener= new PreferenceListener();

/**
	 * Creates a new Java text tools collection.
	 * 
	 * @param store the preference store to initialize the text tools. The text tool
	 * instance installs a listener on the passed preference store to adapt itself to 
	 * changes in the preference store. In general <code>PreferenceConstants.
	 * getPreferenceStore()</code> shoould be used to initialize the text tools.
	 *  * A connection has occured - initialize the receiver if it is the first activation.
	 * 
	 */
	public MTLEditorEnvironment(IPreferenceStore store) {
	  fPreferenceStore = store;
	  fPreferenceStore.addPropertyChangeListener(fPreferenceListener);

	  fColorManager = new MTLColorManager();
	  fCodeScanner = new MTLCodeScanner(fColorManager, store);
	  fMultilineCommentScanner = new SingleTokenScanner(fColorManager, store, PreferencesConstants.MTL_MULTILINE_COMMENT);
	  fSinglelineCommentScanner = new SingleTokenScanner(fColorManager, store, PreferencesConstants.MTL_SINGLELINE_COMMENT);
	  fStringScanner = new SingleTokenScanner(fColorManager, store, PreferencesConstants.MTL_STRING);
	  fPartitionScanner = new FastMTLPartitionScanner();
	}

	
	/**
	 * Disposes all the individual tools of this tools collection.
	 */
	public void dispose() {

	  fCodeScanner = null;
	  fMultilineCommentScanner = null;
	  fSinglelineCommentScanner = null;
	  fStringScanner = null;
//	  fPHPDocScanner = null;
	  fPartitionScanner = null;

	  if (fColorManager != null) {
		fColorManager.dispose();
		fColorManager = null;
	  }

	  if (fPreferenceStore != null) {
		fPreferenceStore.removePropertyChangeListener(fPreferenceListener);
		fPreferenceStore = null;
		fPreferenceListener = null;
	  }
	}

	/**
	 * Returns the color manager which is used to manage
	 * any MTL-specific colors needed for such things like syntax highlighting.
	 *
	 * @return the color manager to be used for MTL text viewers
	 */
	public IColorManager getColorManager() {
	  return fColorManager;
	}

	/**
	 * Returns a scanner which is configured to scan MTL source code.
	 *
	 * @return a MTL source code scanner
	 */
	public RuleBasedScanner getCodeScanner() {
	  return fCodeScanner;
	}

	/**
	 * Returns a scanner which is configured to scan MTL multiline comments.
	 *
	 * @return a MTL multiline comment scanner
	 * 
	 */
	public RuleBasedScanner getMultilineCommentScanner() {
	  return fMultilineCommentScanner;
	}

	/**
	 * Returns a scanner which is configured to scan mtl singleline comments.
	 *
	 * @return a mtl singleline comment scanner
	 * 
	 * @since 2.0
	 */
	public RuleBasedScanner getSinglelineCommentScanner() {
	  return fSinglelineCommentScanner;
	}

	/**
	 * Returns a scanner which is configured to scan mtl strings.
	 *
	 * @return a mtl string scanner
	 * 
	 * @since 2.0
	 */
	public RuleBasedScanner getStringScanner() {
	  return fStringScanner;
	}

	/**
	 * Returns a scanner which is configured to scan 
	 * Java-specific partitions, which are multi-line comments,
	 * JavaDoc comments, and regular Java source code.
	 *
	 * @return a Java partition scanner
	 */
	public IPartitionTokenScanner getPartitionScanner() {
	  return fPartitionScanner;
	}

	/**
	 * Factory method for creating a MTL-specific document partitioner
	 * using this object's partitions scanner. This method is a 
	 * convenience method.
	 *
	 * @return a newly created MTL document partitioner
	 */
	public IDocumentPartitioner createDocumentPartitioner() {

	  String[] types =
		new String[] {
		  FastMTLPartitionScanner.JAVA_DOC,
		  FastMTLPartitionScanner.MTL_MULTI_LINE_COMMENT,
		  FastMTLPartitionScanner.MTL_SINGLE_LINE_COMMENT,
		  FastMTLPartitionScanner.MTL_STRING };

	  return new DefaultPartitioner(getPartitionScanner(), types);
	}

	/**
	 * Returns the names of the document position categories used by the document
	 * partitioners created by this object to manage their partition information.
	 * If the partitioners don't use document position categories, the returned
	 * result is <code>null</code>.
	 *
	 * @return the partition managing position categories or <code>null</code> 
	 * 			if there is none
	 */
	public String[] getPartitionManagingPositionCategories() {
	  return new String[] { DefaultPartitioner.CONTENT_TYPES_CATEGORY };
	}

	/**
	 * Determines whether the preference change encoded by the given event
	 * changes the behavior of one its contained components.
	 * 
	 * @param event the event to be investigated
	 * @return <code>true</code> if event causes a behavioral change
	 * 
	 * @since 2.0
	 */
	public boolean affectsBehavior(PropertyChangeEvent event) {
	  return fCodeScanner.affectsBehavior(event)
		|| fMultilineCommentScanner.affectsBehavior(event)
		|| fSinglelineCommentScanner.affectsBehavior(event)
		|| fStringScanner.affectsBehavior(event);
		//|| fPHPDocScanner.affectsBehavior(event);
	}

	/**
	 * Adapts the behavior of the contained components to the change
	 * encoded in the given event.
	 * 
	 * @param event the event to which to adapt
	 * @since 2.0
	 */
	protected void adaptToPreferenceChange(PropertyChangeEvent event) {
	  if (fCodeScanner.affectsBehavior(event))
		fCodeScanner.adaptToPreferenceChange(event);
	  if (fMultilineCommentScanner.affectsBehavior(event))
		fMultilineCommentScanner.adaptToPreferenceChange(event);
	  if (fSinglelineCommentScanner.affectsBehavior(event))
		fSinglelineCommentScanner.adaptToPreferenceChange(event);
	  if (fStringScanner.affectsBehavior(event))
		fStringScanner.adaptToPreferenceChange(event);
//	  if (fPHPDocScanner.affectsBehavior(event))
//		fPHPDocScanner.adaptToPreferenceChange(event);
	}
}

	