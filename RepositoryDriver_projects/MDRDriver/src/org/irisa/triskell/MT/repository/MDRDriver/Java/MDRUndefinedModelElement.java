package org.irisa.triskell.MT.repository.MDRDriver.Java;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import javax.jmi.xmi.XmiReader;
import javax.jmi.xmi.XmiWriter;
import org.netbeans.api.mdr.MDRManager;
import org.netbeans.api.mdr.MDRepository;
import javax.jmi.reflect.RefPackage;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelRole;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.apache.log4j.Logger;
import org.netbeans.api.mdr.CreationFailedException;
import javax.jmi.reflect.RefClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import javax.jmi.reflect.RefObject;
import java.util.List;
import java.util.Iterator;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;

abstract public class MDRUndefinedModelElement 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelElement
{
    protected static final org.irisa.triskell.MT.repository.API.Java.CommonException NullPointerCommonException = new org.irisa.triskell.MT.repository.API.Java.CommonException("Null pointer exception.");
    public static org.irisa.triskell.MT.repository.API.Java.CommonException getNullPointerCommonException () {
        return MDRUndefinedModelElement.NullPointerCommonException;
    }
    public static int cardNullPointerCommonException () {
        if ( MDRUndefinedModelElement.NullPointerCommonException == null ) return 0;
        else return 1;
    }


    MDRUndefinedModelElement(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
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
		throw MDRAPI.getNullPointerException();
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

    public org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement p0,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation p1,
        org.irisa.triskell.MT.DataTypes.Java.Value[] p2)
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
		throw MDRAPI.getNullPointerException();
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
