/* $Id: EMFMetaObject.java,v 1.1 2004-04-28 10:20:50 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;

import org.eclipse.emf.edit.command.*;



/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFMetaObject
	extends EMFFeatured
	implements TypeValue
{
	private final EMFMetaClass metaClass;
	protected EMFMetaClass getMetaClass () {
		return this.metaClass;
	}
	protected int cardMetaClass () {
		if ( this.metaClass == null ) return 0;
		else return 1;
	}


	public EMFMetaObject(
		EMFMetaClass metaClass)
	{
		super(false, null, metaClass.getSpecificAPI(), metaClass.getRefClass());
		this.metaClass = metaClass;
	}

	public boolean equals(
		org.irisa.triskell.MT.DataTypes.Java.Value rhs)
	{
		return (rhs instanceof EMFMetaObject)
			 && (this.getMetaClass().equals(((EMFMetaObject)rhs).getMetaClass()));
	}

	public String toString()
	{
		return this.getMetaClass().toString();
	}

	public void delete() {}
	
	public void deleteTheModelElement () {
		this.delete();
	}
    
	public boolean isKindOf(MetaClass classifier)
	{
		return this.getMetaClass().conformsTo(classifier);
	}

	public boolean isMetaObject()
	{
		return true;
	}

	public boolean isTypeOf(MetaClass classifier)
	{
		return this.getMetaClass().equals(classifier);
	}

	public EMFChildElement getRefClass()
	{
		return this.getMetaClass().getRefClass();
	}

	public Type getType() {
		return OclTypeType.TheInstance;
	}

	protected CommandGroup getBaseCommandGroup() {
		return OclTypeCommandGroup.TheInstance;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.TypeValue#getTheType()
	 */
	public Type getTheType() {
		return this.getMetaClass(); 
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue#getValue()
	 */
	public String getValue() {
		return this.getMetaClass().getQualifiedNameAsString();
	}



}
