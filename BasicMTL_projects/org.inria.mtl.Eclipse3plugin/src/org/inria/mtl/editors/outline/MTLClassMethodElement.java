/**
 * <p>Titre : MTLClassMethodElement </p>
 * <p>Description: Definition d'une m�thode MTL</p>
 * <p>Projet : EfEPlugin</p>
 * <p>Description Projet : MTL for Eclipse</p>
 * <p>Derni�re Modification : 06-04-04</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : Universit� de Tours - DESS SIR Blois</p>
 * @author Audineau F. - H�lias M.
 * @version 1.0
 */


package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLClassMethodElement extends MTLElement
{
	public MTLClassMethodElement(String aName,  int offset, int length)
	{
		super(aName, offset, length);
	}
	
	public ImageDescriptor getImageDescriptor(Object object)
	{	/* Voir MTLImages pour les icones*/
		return MTLImages.ICON_VIEW_CLASS_METHOD;
	}

	public int category()
	{
		return CLASS_METHOD;	
	}

}
