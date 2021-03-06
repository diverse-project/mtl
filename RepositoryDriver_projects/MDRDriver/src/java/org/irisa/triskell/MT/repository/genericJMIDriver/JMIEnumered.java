/*
 * $Id: JMIEnumered.java,v 1.1 2004-10-25 12:32:56 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIEnumered 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.DataTypes.Java.EnumValue
{
    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaEnumeration type;

    private final javax.jmi.reflect.RefEnum refEnum;


    public JMIEnumered(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaEnumeration type,
        javax.jmi.reflect.RefEnum refEnum)
    {
        super(type.getSpecificAPI(), refEnum);
		this.type = type;
		this.refEnum = refEnum;
    }

    public String toString()
    {
		return "enumered " + this.getTheModelElement();
    }

    public boolean isMetaObject()
    {
		return false;
    }

    public void delete()
    {
    }
    
    public void deleteTheModelElement () {
    	this.delete();
    }

    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.getType().equals(classifier);
    }

    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.getType().conformsTo(classifier);
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    public void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(argument);
    }

    public Value invokeQueryOperation(
        ModelElement contextualElement,
        MetaOperation feature,
        Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    public String getTheModelElement()
    {
		return this.getType() + "::" + this.getValue();
    }

    public boolean isUndefined()
    {
		return false;
    }

    public String getErrorMessage()
    {
		return null;
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (this == rhs) || ((rhs instanceof EnumValue) ? (((EnumValue)rhs).getEnumeration().equals(this.getEnumeration())) && (((EnumValue)rhs).getTheEnum().equals(this.getTheEnum())) : rhs != null && rhs.equals(this));
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, MultipleCommandException
    {
		//throw new org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException(this, name, arguments, discriminants);
		return this.type.getCommandGroup().invoke(scopeQualifiedName, this, name, arguments, discriminants);
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		//visitor.visitEnumValue(this);
		visitor.visitModelElementValue(this);
    }

    public String getTheEnum()
    {
		return this.refEnum.toString();
    }

    public String[] getEnumeration()
    {
		return this.type.getQualifiedName();
    }

    public String getValue()
    {
		return this.getTheEnum();
    }

    public Type getType()
    {
    	return this.type;
    }

    public String getUniqId()
    {
		return this.getTheModelElement();
    }

	protected void cache() {
		this.getSpecificAPI().setCachedModelElement(this);
	}
}
