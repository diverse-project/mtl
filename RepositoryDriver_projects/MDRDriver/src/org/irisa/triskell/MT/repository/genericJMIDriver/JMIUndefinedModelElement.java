/**
 * $Id: JMIUndefinedModelElement.java,v 1.2 2004-07-30 13:20:13 ffondeme Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
abstract public class JMIUndefinedModelElement 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelElement
{
    protected static final org.irisa.triskell.MT.repository.API.Java.CommonException NullPointerCommonException = JMIAPI.getNullPointerException();
    public static org.irisa.triskell.MT.repository.API.Java.CommonException getNullPointerCommonException () {
        return JMIUndefinedModelElement.NullPointerCommonException;
    }
    public static int cardNullPointerCommonException () {
        if ( JMIUndefinedModelElement.NullPointerCommonException == null ) return 0;
        else return 1;
    }
    JMIUndefinedModelElement(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
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
		throw JMIAPI.getNullPointerException();
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
		throw JMIAPI.getNullPointerException();
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

	protected void cache() {
	}
}
