/*
 * Package créé le 25 février 2004, debuggé et modifié le 27
 * Pamplemouss
 */

package org.inria.mtl.editors.outline;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;


public class MTLSyntaxModelFactory
{
	private static MTLSyntaxModelFactory instance = new MTLSyntaxModelFactory();
	private boolean registryLoaded = false;

	private MTLSyntaxModelFactory()
	{
	}

	public MTLElementList getContentOutline(IFile adaptable)
	{
		return new MTLElementList(getSyntacticElements(adaptable));
	}

	public MTLElementList getContentOutline(IDocument document)
	{
		return new MTLElementList(getSyntacticElements(document));
	}

	public static MTLSyntaxModelFactory getInstance()
	{
		return instance;
	}

	private List getSyntacticElements(IFile file)
	{
		return (new MTLParser()).parse(file);
	}

	private List getSyntacticElements(IDocument document)
	{
		return (new MTLParser()).parse(document);
	}
}