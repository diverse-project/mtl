/* $Id: EMFUndefinedModelElement.java,v 1.1 2004-03-08 08:18:18 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class EMFUndefinedModelElement
	extends EMFElement
	implements org.irisa.triskell.MT.repository.API.Java.ModelElement
{
	protected static final org.irisa.triskell.MT.repository.API.Java.CommonException NullPointerCommonException = EMFAPI.getNullPointerException();
	public static org.irisa.triskell.MT.repository.API.Java.CommonException getNullPointerCommonException () {
		return EMFUndefinedModelElement.NullPointerCommonException;
	}
	public static int cardNullPointerCommonException () {
		if ( EMFUndefinedModelElement.NullPointerCommonException == null ) return 0;
		else return 1;
	}


	EMFUndefinedModelElement(
		EMFAPI api)
	{
		super(api, null);
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
		String [] scopeQualifiedName,
		String name,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
		String[] discriminants)
		throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException
	{
		return org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl.getNullPointerUndefined();
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#delete() 
	  */
	public void delete()
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException
	{
		throw EMFAPI.getNullPointerException();
	}

	public boolean isKindOf(
		org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
	{
		return true;
	}

	public boolean isTypeOf(
		org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
	{
		return true;
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
	{
		return org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl.getNullPointerUndefined();
	}

	public Value invokeQueryOperation(
		ModelElement p0,
		MetaOperation p1,
		Value[] p2)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IsQueryException
	{
		return org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl.getNullPointerUndefined();
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#setAttributeValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAttribute, org.irisa.triskell.MT.DataTypes.Java.Value) 
	  */
	public void setAttributeValue(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
		org.irisa.triskell.MT.DataTypes.Java.Value value)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
	{
		throw EMFAPI.getNullPointerException();
	}

	public boolean isMetaObject()
	{
		return false;
	}

	public boolean isUndefined()
	{
		return true;
	}

	public void accept(
		org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
	{
		visitor.visitModelElementValue(this);
	}

}
