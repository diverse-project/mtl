package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDREnumered 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.DataTypes.Java.EnumValue
{
    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaEnumeration type;

    private final javax.jmi.reflect.RefEnum refEnum;


    public MDREnumered(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaEnumeration type,
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

    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return classifier.equals(this.type);
    }

    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.isTypeOf(classifier);
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

    public org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
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
		return (this == rhs) || ((rhs instanceof EnumValue) && (((EnumValue)rhs).getEnumeration().equals(this.getEnumeration())) && (((EnumValue)rhs).getTheEnum().equals(this.getTheEnum())));
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
		visitor.visitEnumValue(this);

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

    public String getType()
    {
		return org.irisa.triskell.MT.utils.Java.AWK.merge(this.getEnumeration(), "::");
    }

    public String getUniqId()
    {
		return this.toString();
    }
}
