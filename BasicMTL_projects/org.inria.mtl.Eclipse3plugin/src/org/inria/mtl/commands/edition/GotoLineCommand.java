/*
 * Created on 27 oct. 2004
 *
 */
package org.inria.mtl.commands.edition;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 */
public class GotoLineCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private int lineNumber;
	public int  getLineNumber ()               { return lineNumber; }
	public void setLineNumber (int lineNumber) { this.lineNumber = lineNumber; }

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS 
	////////////////////////////////////////////////////////////////////////////////
	
	/**  */
	public GotoLineCommand (int lineNumber) 
	{
		setLineNumber (lineNumber);
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS 
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute() throws Exception 
	{
		IEditorPart editorPart = MTLPlugin.getActivePage().getActiveEditor();
		
		if (editorPart instanceof ITextEditor)
		{
			ITextEditor editor = (ITextEditor) editorPart;
			IDocumentProvider provider= editor.getDocumentProvider();
			IDocument document= provider.getDocument(editor.getEditorInput());
			try {
				int start= document.getLineOffset(getLineNumber()-1);			
				editor.selectAndReveal(start, 0);
				
				IWorkbenchPage page= editor.getSite().getPage();
				page.activate(editor);
				
			} catch (BadLocationException x) {
			}
		}
		return null;
	}
	
}
