/*
 * Created on 04-05-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.plugin.editors;

//import ish.eclipse.etex.preferences.TexPreferencePage;
//import ish.etex.spell.SpellingRule;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


import org.inria.mtl.plugin.MTLPlugin;

/**
 * @author Serge DZALE
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MTLConfig extends SourceViewerConfiguration {
	private ColorManager colorManager;
	private String language;
	
	public void setLanguage(String language){
		this.language = language;
	}

	private static Color DEFAULT_TAG_COLOR =
		new Color(Display.getCurrent(), new RGB(0, 0, 0));

	public MTLConfig(ColorManager colorManager) {
		this.colorManager = colorManager;
	}


	/**
	 * Define reconciler for MyEditor
	 */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		RuleBasedScanner codescanner = new RuleBasedScanner();

		IToken commandToken =
			new Token(
				new TextAttribute(
					colorManager.getColor(TexPreferencePage.COMMAND_COLOR)));
		IToken commentToken =
			new Token(
				new TextAttribute(
					colorManager.getColor(TexPreferencePage.COMMENT_COLOR)));
		IToken curlyToken =
			new Token(
				new TextAttribute(
					colorManager.getColor(TexPreferencePage.ARGUMENT_COLOR)));
		IToken squareToken =
			new Token(
				new TextAttribute(
					colorManager.getColor(TexPreferencePage.OPTIONAL_COLOR)));
					
		IToken mathToken =
			new Token(
				new TextAttribute(
						colorManager.getColor(TexPreferencePage.MATH_COLOR))); // Stephan Dlugosz 19.11.2003
		IToken numberToken =
					new Token(
						new TextAttribute(
								colorManager.getColor(TexPreferencePage.TEXT_COLOR))); // Stephan Dlugosz 19.11.2003

		//Add rule for processing instructions
		//Add rule for processing instructions
		IRule[] rules;
		if (MTLPlugin.getDefault().getPreferenceStore().getBoolean(TexPreferencePage.MATH_MARK)) { // Stephan DLugosz 24.11.2003
			rules = new IRule[11]; //IRule[] rules = new IRule[5]; Stephan Dlugosz 19.11.2003
			
			rules[0] = new EndOfLineRule("%", commentToken);
			rules[1] = new MultiLineRule("{", "}", curlyToken);
			rules[2] = new SingleLineRule("\\\\"," ",commandToken);
			rules[3] = new MultiLineRule("\\[","\\]",mathToken);
			rules[4] = new SingleLineRule("[", "]", squareToken);
			rules[5] = new MultiLineRule("\\begin{equation}","\\end{equation}", mathToken); // Stephan Dlugosz 19.11.2003
			rules[6] = new MultiLineRule("\\begin{equation*}","\\end{equation*}", mathToken); // Stephan Dlugosz 19.11.2003
			//rules[2] = new SingleLineRule("[", "]", squareToken);
			//rules[3] = new WordRule(new TexWords(), commandToken);
			//rules[4] = new SpellingRule(colorManager); 
			rules[7] = new WordRule(new TexWords(), commandToken);
			rules[8] = new WordRule(new NumberWord(), numberToken); // Stephan Dlugosz 19.11.2003
			rules[9] = new SingleLineRule("$","$", mathToken); // Stephan Dlugosz 19.11.2003
			rules[10] = new SpellingRule(colorManager,language); 
		} else {
			rules = new IRule[6];
			
			rules[0] = new EndOfLineRule("%", commentToken);
			rules[1] = new MultiLineRule("{", "}", curlyToken);
			rules[2] = new SingleLineRule("[", "]", squareToken);
			rules[3] = new WordRule(new TexWords(), commandToken);
			rules[4] = new WordRule(new NumberWord(), numberToken); // Stephan Dlugosz 19.11.2003
			rules[5] = new SpellingRule(colorManager,language); 
		}
		
		codescanner.setRules(rules);
		
		
		
//		IRule[] rules = new IRule[5];
//			
//
//
//		rules[0] = new EndOfLineRule("%", commentToken);
//		rules[1] = new MultiLineRule("{", "}", curlyToken);
//		rules[2] = new SingleLineRule("[", "]", squareToken);
//		rules[3] = new WordRule(new TexWords(), commandToken);
//		rules[4] = new SpellingRule(colorManager);
//
//		codescanner.setRules(rules);

		TexDamagerRepairer dr = new TexDamagerRepairer(codescanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(
			new TexCompletion(),
			IDocument.DEFAULT_CONTENT_TYPE);

		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500);
		assistant.setProposalPopupOrientation(
			IContentAssistant.PROPOSAL_OVERLAY);
		assistant.setContextInformationPopupOrientation(
			IContentAssistant.CONTEXT_INFO_ABOVE);
		return assistant;
	}

}
