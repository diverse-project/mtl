/* $Id: EMFMetaElement.java,v 1.1 2004-04-28 10:20:46 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class EMFMetaElement
	extends EMFElement
	implements org.irisa.triskell.MT.repository.API.Java.MetaElement
 {
	private final String name;

	private final String[] qualifiedName;
    
	private transient String qualifiedNameAsString = null;


	public EMFMetaElement(
		EMFAPI api,
		java.lang.Object ref,
		String name,
		String[] qualifiedName)
	{
		super(api, ref);
		this.name = name == null ? (qualifiedName == null ? null : qualifiedName[qualifiedName.length-1]) : name;
		this.qualifiedName = qualifiedName;
	}

	public String getName()
	{
		return this.name;
	}

	public String[] getQualifiedName()
	{
		return this.qualifiedName;
	}

	public String getQualifiedNameAsString()
	{
		if (this.qualifiedNameAsString == null)
			this.qualifiedNameAsString = AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
		return this.qualifiedNameAsString;
	}

}
