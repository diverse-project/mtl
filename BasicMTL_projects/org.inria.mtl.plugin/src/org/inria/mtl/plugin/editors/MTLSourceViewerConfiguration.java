package org.inria.mtl.plugin.editors;

import java.util.Vector;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;

import org.eclipse.jdt.internal.ui.text.java.JavaAutoIndentStrategy;
import org.eclipse.jdt.internal.ui.text.java.JavaDoubleClickSelector;
import org.eclipse.jdt.internal.ui.text.java.JavaStringAutoIndentStrategy;
import org.eclipse.jdt.internal.ui.text.java.JavaStringDoubleClickSelector;
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDocAutoIndentStrategy;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultTextDoubleClickStrategy;
import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import org.inria.mtl.plugin.editors.completion.MTLCompletionProcessor;
import org.inria.mtl.plugin.editors.completion.ContentAssistPreferences;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.editors.utils.IColorManager;
import org.inria.mtl.plugin.editors.utils.MTLEditorEnvironment;
import org.inria.mtl.plugin.editors.utils.MTLPartitionScanner;
import org.inria.mtl.plugin.preferences.PreferenceConstants;

/**
 * Configuration for an <code>SourceViewer</code> which shows MTL code.
 */
public class MTLSourceViewerConfiguration extends SourceViewerConfiguration {

	/** 
	 * Preference key used to look up display tab width.
	 * 
	 */
	public final static String PREFERENCE_TAB_WIDTH= PreferenceConstants.EDITOR_TAB_WIDTH;
	/** 
	 * Preference key for inserting spaces rather than tabs.
	 * 
	 */
	public final static String SPACES_FOR_TABS= PreferenceConstants.EDITOR_SPACES_FOR_TABS;
	
	private MTLEditorEnvironment fMTLEditorEnvironment;
	private MTLEditor fTextEditor;
	
	/**
	 * Creates a new MTL source viewer configuration for viewers in the given editor 
	 * using the given MTL tools.
	 *
	 * @param tools the MTL tools to be used
	 * @param editor the editor in which the configured viewer(s) will reside
	 */
	public MTLSourceViewerConfiguration(MTLEditorEnvironment tools, MTLEditor editor) {
		fMTLEditorEnvironment= tools;
		fTextEditor= editor;
	}
	
	/**
	 * Returns the MTL source code scanner for this configuration.
	 *
	 * @return the MTL source code scanner
	 */
	protected RuleBasedScanner getCodeScanner() {
		return fMTLEditorEnvironment.getCodeScanner();
	}
	
	/**
	 * Returns the MTL multiline comment scanner for this configuration.
	 *
	 * @return the MTL multiline comment scanner
	 */
	protected RuleBasedScanner getMultilineCommentScanner() {
		return fMTLEditorEnvironment.getMultilineCommentScanner();
	}
	
	/**
	 * Returns the MTL singleline comment scanner for this configuration.
	 *
	 * @return the MTL singleline comment scanner
	 * 
	 */
	protected RuleBasedScanner getSinglelineCommentScanner() {
		return fMTLEditorEnvironment.getSinglelineCommentScanner();
	}
	
	/**
	 * Returns the Java string scanner for this configuration.
	 *
	 * @return the Java string scanner
	 */

	protected RuleBasedScanner getStringScanner() {
		return fMTLEditorEnvironment.getStringScanner();
	}
	
	/**
	 * Returns the color manager for this configuration.
	 *
	 * @return the color manager
	 */
	protected IColorManager getColorManager() {
		return fMTLEditorEnvironment.getColorManager();
	}
	
	/**
	 * Returns the editor in which the configured viewer(s) will reside.
	 *
	 * @return the enclosing editor
	 */
	protected MTLEditor getEditor() {
		return fTextEditor;
	}
	
	/**
	 * Returns the preference store used by this configuration to initialize
	 * the individual bits and pieces.
	 * 
	 * @return the preference store used to initialize this configuration
	 * 
	 */
	protected IPreferenceStore getPreferenceStore() {
		return MTLPlugin.getDefault().getPreferenceStore();
	}
	
	/** 
	 *  Returns all configured content types for the given source viewerMethod declared on SourceViewerConfiguration
	 */
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
	  return new String[] {
	  	IDocument.DEFAULT_CONTENT_TYPE,
		MTLPartitionScanner.MTL_DOC,
		MTLPartitionScanner.MTL_MULTI_LINE_COMMENT,
		MTLPartitionScanner.MTL_SINGLE_LINE_COMMENT,
		MTLPartitionScanner.MTL_STRING
		};
	}

	/** 
	 * Returns all configured content types for the given source viewer
	 */
//	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
//		
//	 if (getEditor()!=null){
//			ContentAssistant assistant = new ContentAssistant();
//			 IContentAssistProcessor processor = new HTMLCompletionProcessor();
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.HTML);
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.HTML_MULTILINE_COMMENT);
//			 assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.CSS);
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.CSS_MULTILINE_COMMENT);
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.JAVASCRIPT);
//			 assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.JS_MULTILINE_COMMENT);
//	  
//	 	
//	 }

	  // TODO define special smarty partition content assist
//	  assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.SMARTY);
//		  assistant.setContentAssistProcessor(processor, IPHPPartitionScannerConstants.SMARTY_MULTILINE_COMMENT);
//
//	  assistant.setContentAssistProcessor(new PHPCompletionProcessor(), IPHPPartitionScannerConstants.PHP);
//
//	  assistant.setContentAssistProcessor(new PHPDocCompletionProcessor(), IPHPPartitionScannerConstants.PHP_MULTILINE_COMMENT);
//	  ContentAssistPreference.configure(assistant, getPreferenceStore());
//
//	  assistant.setContextInformationPopupOrientation(ContentAssistant.CONTEXT_INFO_ABOVE);
//	  assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));

//	  return assistant;
//	}
	/**
	 * Returns the prefixes to be used by the line-shift operation.
	 */
	public String[] getDefaultPrefixes(ISourceViewer sourceViewer, String contentType) {
	  return new String[] { "//", "" }; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 * Returns the double-click strategy ready to be used in this viewer when double clicking onto text of the given content type
	 * 
	 */
//	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
//	  return new PHPDoubleClickSelector();
//	}


	/** 
	 * Returns the auto indentation strategy ready to be used with the given source viewer when manipulating text of the given content type.
	 * 
	 */
	public IAutoIndentStrategy getAutoIndentStrategy(ISourceViewer sourceViewer, String contentType) {
		if (MTLPartitionScanner.MTL_DOC.equals(contentType) ||
				MTLPartitionScanner.MTL_MULTI_LINE_COMMENT.equals(contentType))
			return new JavaDocAutoIndentStrategy();
		else if (MTLPartitionScanner.MTL_STRING.equals(contentType))
			return new JavaStringAutoIndentStrategy();
		return new JavaAutoIndentStrategy();
	}

	/** 
	 * Returns the auto indentation strategy ready to be used with the given source viewer when manipulating text of the given content type.
	 * 
	 */
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		ContentAssistant assistant= new ContentAssistant();
		IContentAssistProcessor processor = new MTLCompletionProcessor();
		assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(processor, MTLPartitionScanner.MTL_DOC);
		assistant.setContentAssistProcessor(processor, MTLPartitionScanner.MTL_SINGLE_LINE_COMMENT);
		assistant.setContentAssistProcessor(processor, MTLPartitionScanner.MTL_MULTI_LINE_COMMENT);
		assistant.setContentAssistProcessor(processor, MTLPartitionScanner.MTL_STRING);
		

		ContentAssistPreferences.configure(assistant, getPreferenceStore());
		//assistant.setProposalPopupOrientation(ContentAssistant.CONTEXT_INFO_ABOVE);
		assistant.setContextInformationPopupOrientation(ContentAssistant.CONTEXT_INFO_ABOVE);
		assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		System.out.println("Passé par contentAssist");
		return assistant; 
	}
	
	/**
	 * Returns the prefixes to be used by the line-shift operation
	*/
   public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {

	   Vector vector= new Vector();

	   // prefix[0] is either '\t' or ' ' x tabWidth, depending on useSpaces
				
	   Preferences preferences= JavaCore.getPlugin().getPluginPreferences();
	   int tabWidth= preferences.getInt(JavaCore.FORMATTER_TAB_SIZE);
	   boolean useSpaces= getPreferenceStore().getBoolean(SPACES_FOR_TABS);
		
	   for (int i= 0; i <= tabWidth; i++) {
		   StringBuffer prefix= new StringBuffer();

		   if (useSpaces) {
			   for (int j= 0; j + i < tabWidth; j++)
				   prefix.append(' ');
		    	
			   if (i != 0)
				   prefix.append('\t');				
		   } else {    
			   for (int j= 0; j < i; j++)
				   prefix.append(' ');
		    	
			   if (i != tabWidth)
				   prefix.append('\t');
		   }
			
		   vector.add(prefix.toString());
	   }

	   vector.add(""); //$NON-NLS-1$
		
	   return (String[]) vector.toArray(new String[vector.size()]);
   }


	
	/**
	 * Returns the presentation reconciler ready to be used with the given source viewer. This implementation always returns null. 
	 */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {

		PresentationReconciler reconciler= new PresentationReconciler();

		// rule for default text
		DefaultDamagerRepairer dr= new DefaultDamagerRepairer(getCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		// rule for multiline comments
		// We just need a scanner that does nothing but returns a token with the corrresponding text attributes
			dr= new DefaultDamagerRepairer(getMultilineCommentScanner());
		reconciler.setDamager(dr, MTLPartitionScanner.MTL_MULTI_LINE_COMMENT);
		reconciler.setRepairer(dr, MTLPartitionScanner.MTL_MULTI_LINE_COMMENT);

		//rule for MTL single line comments
		dr= new DefaultDamagerRepairer(getSinglelineCommentScanner());
		reconciler.setDamager(dr, MTLPartitionScanner.MTL_SINGLE_LINE_COMMENT);
		reconciler.setRepairer(dr, MTLPartitionScanner.MTL_SINGLE_LINE_COMMENT);

//		rule for MTL string
		dr= new DefaultDamagerRepairer(getStringScanner());
		reconciler.setDamager(dr, MTLPartitionScanner.MTL_STRING);
		reconciler.setRepairer(dr, MTLPartitionScanner.MTL_STRING);

		
		return reconciler;
	}
	
	/**
	 * Returns the visual width of the tab character
	 */
	public int getTabWidth(ISourceViewer sourceViewer) {
		return this.getPreferenceStore().getInt(PREFERENCE_TAB_WIDTH);
	}
	

	/*
	 * @see SourceViewerConfiguration#getDoubleClickStrategy(ISourceViewer, String)
	 */
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (MTLPartitionScanner.MTL_DOC.equals(contentType) ||
				MTLPartitionScanner.MTL_MULTI_LINE_COMMENT.equals(contentType) ||
				MTLPartitionScanner.MTL_SINGLE_LINE_COMMENT.equals(contentType))
			return new DefaultTextDoubleClickStrategy();
		else if (MTLPartitionScanner.MTL_STRING.equals(contentType))
			return new JavaStringDoubleClickSelector();
		return new JavaDoubleClickSelector();
	}
	

	
}
