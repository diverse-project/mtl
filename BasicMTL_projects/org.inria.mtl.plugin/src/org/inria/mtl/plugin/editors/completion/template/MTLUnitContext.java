package org.inria.mtl.plugin.editors.completion.template;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * A compilation unit context.
 */
public class MTLUnitContext extends DocumentTemplateContext {

  /** The platform default line delimiter. */
  private static final String PLATFORM_LINE_DELIMITER = System.getProperty("line.separator"); //$NON-NLS-1$

  private static final String specialChars = "$";
  /** The compilation unit, may be <code>null</code>. */
  //	private final ICompilationUnit fCompilationUnit;
	protected boolean fForceEvaluation;
  /**
   * Creates a compilation unit context.
   * 
   * @param type   the context type.
   * @param document the document.
   * @param completionPosition the completion position within the document.
   * @param compilationUnit the compilation unit (may be <code>null</code>).
   */
  protected MTLUnitContext(ContextType type, IDocument document, int completionPosition)
    {
	super(type, document, completionPosition, 0);
    }
	
	protected MTLUnitContext(ContextType type, IDocument document, int completionPosition, int completionLength)
		{
			super(type, document, completionPosition, completionLength);
		}
		
  /*
  * @see TemplateContext#canEvaluate(Template templates)
  */
  public boolean canEvaluate(Template template) {
	return template.matches(getKey(), getContextType().getName());
  }

  /**
   * Returns <code>true</code> if template matches the prefix and context,
   * <code>false</code> otherwise.
   */
  public boolean canEvaluate(String identifier) {
	String prefix = getKey();
	return
	 identifier.toLowerCase().startsWith(prefix.toLowerCase());
  }

  /*
  * @see TemplateContext#evaluate(Template template)
  */
  public TemplateBuffer evaluate(Template template) throws CoreException {
	if (!canEvaluate(template))
	  return null;

	TemplateTranslator translator = new TemplateTranslator();
	TemplateBuffer buffer = translator.translate(template.getPattern());

	getContextType().edit(buffer, this);

	String lineDelimiter = null;
	try {
	  lineDelimiter = getDocument().getLineDelimiter(0);
	} catch (BadLocationException e) {
	}

	if (lineDelimiter == null)
	  lineDelimiter = PLATFORM_LINE_DELIMITER;

	return buffer;
  }

  /*
   * @see DocumentTemplateContext#getCompletionPosition();
   */
  public int getStart() {
	IDocument document = getDocument();
	try {
	  int start = getCompletionOffset();

	  if ( ((start != 0) && specialChars.indexOf(document.getChar(start - 1)) != (-1) )) {
		return --start;
	  }
	  
	  while (((start != 0) && Character.isUnicodeIdentifierPart(document.getChar(start - 1)))
		|| ((start != 0) && specialChars.indexOf(document.getChar(start - 1)) != (-1) )) {
		start--;
	  }

	  if (((start != 0) && Character.isUnicodeIdentifierStart(document.getChar(start - 1)))
		|| ((start != 0) && specialChars.indexOf(document.getChar(start - 1)) != (-1) )) {
		start--;
	  }

	  return start;

	} catch (BadLocationException e) {
	  return getCompletionOffset();
	}
  }

  /**
   * Returns the character before start position of completion.
   */
  public char getCharacterBeforeStart() {
	int start = getStart();

	try {
	  return start == 0 ? ' ' : getDocument().getChar(start - 1);

	} catch (BadLocationException e) {
	  return ' ';
	}
  }
  
  /**
   * Returns the compilation unit if one is associated with this context, <code>null</code> otherwise.
   */
  //	public final ICompilationUnit getCompilationUnit() {
  //		return fCompilationUnit;
  //	}

  /**
   * Returns the enclosing element of a particular element type, <code>null</code>
   * if no enclosing element of that type exists.
   */
  //	public IJavaElement findEnclosingElement(int elementType) {
  //		if (fCompilationUnit == null)
  //			return null;
  //
  //		try {
  //			IJavaElement element= fCompilationUnit.getElementAt(getStart());
  //			while (element != null && element.getElementType() != elementType)
  //				element= element.getParent();
  //			
  //			return element;
  //
  //		} catch (JavaModelException e) {
  //			return null;
  //		}	
  //	}

  /**
   * @param b
   */
  public void setForceEvaluation(boolean b) {
	fForceEvaluation = b;
  }

}
