/**
 * <p>Titre : EiffelInstanceMethodElement </p>
 * <p>Description: Instance de methode</p>
 * <p>Projet : EfEPlugin</p>
 * <p>Description Projet : Eiffel for Eclipse</p>
 * <p>Dernière Modification : 06-04-04</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Société : Université de Tours - DESS SIR Blois</p>
 * @author Audineau F. - Hélias M.
 * @version 1.0
 */

package org.inria.mtl.editors.outline;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author fitzpata
 *
 */
public class MTLInstanceMethodElement extends MTLFunctionElement
{
	public MTLInstanceMethodElement(String aName, String argumentString, int offset, int length)
	{
		super(aName, argumentString, offset, length);
	}
	
	public ImageDescriptor getImageDescriptor(Object object)
	{
		return MTLImages.ICON_VIEW_INSTANCE_METHOD;
	}

	public int category()
	{
		return INSTANCE_METHOD;	
	}

}
