/**
 * <p>Titre : MTLInstanceVariableElement </p>
 * <p>Description: Instance d'une variable</p>
 * <p>Projet : EfEPlugin</p>
 * <p>Description Projet : MTL for Eclipse</p>
 * <p>Dernière Modification : 06-04-04</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Société : Université de Tours - DESS SIR Blois</p>
 * @author Audineau F. - Hélias M.
 * @version 1.0
 */

package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLInstanceVariableElement extends MTLElement
{
	public MTLInstanceVariableElement(String aName, int offset, int length)
	{
		super(aName, offset, length);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{	/* Voir MTLImages pour les icones*/
		return MTLImages.ICON_VIEW_INSTANCE_VARIABLE;
	}

	public int category(){ return INSTANCE_VARIABLE;}

}
