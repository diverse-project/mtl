package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDRMetaStructure 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.ModelElement
{
    private final javax.jmi.reflect.RefClass classContainer;

    private final javax.jmi.reflect.RefPackage packageContainer;

    private final javax.jmi.model.StructureType refMetaObject;

    private transient javax.jmi.model.StructureField[] fields = null;


    public MDRMetaStructure(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.model.StructureType metaObject,
        javax.jmi.reflect.RefPackage packageContainer,
        javax.jmi.reflect.RefClass classContainer)
    {
        super(api, metaObject, null, retreiveQualifiedName(metaObject));
		this.refMetaObject = metaObject;
		this.packageContainer = packageContainer;
		this.classContainer = classContainer;
    }

    public MDRMetaStructure(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.model.StructureType metaObject,
        javax.jmi.reflect.RefPackage packageContainer)
    {
		this(api, metaObject, packageContainer, null);
    }

    public MDRMetaStructure(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.model.StructureType metaObject,
        javax.jmi.reflect.RefClass classContainer)
    {
		this(api, metaObject, null, classContainer);
    }

    public boolean isUndefined()
    {
		return false;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, MultipleCommandException
    {
		return OclAnyCommandGroup.TheInstance.invoke(scopeQualifiedName, this, name, arguments, discriminants);
    }

    public String getErrorMessage()
    {
		return null;
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (this == rhs) || ((rhs instanceof MDRMetaStructure) && (this.refMetaObject.equals(((MDRMetaStructure)rhs).refMetaObject)));
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitModelElementValue(this);
    }

    public String getTheModelElement()
    {
		return this.refMetaObject.refMofId();
    }

    public void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
	throw new UnknownElementException(argument);
    }

    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.equals(classifier);
    }

    public boolean isMetaObject()
    {
		return true;
    }

    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.isTypeOf(classifier);
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    public String getUniqId()
    {
		return this.refMetaObject.refMofId();
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject()
    {
		return this;
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstances()
    {
		return new MDRModelElementIterator(this.getSpecificAPI(), new java.util.ArrayList(0));
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstancesWithConstraint(
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
    {
		return new MDRModelElementIterator(this.getSpecificAPI(), new java.util.ArrayList(0));
    }

    public String getName()
    {
		return this.refMetaObject.getName();
    }

    public String toString()
    {
		return "structure " + org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
    }

    public void delete()
    {
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
    {
		try {
			java.util.List javaArguments = new java.util.ArrayList(arguments.length);
			for (int i = 0; i < arguments.length; ++i)
				javaArguments.add(this.getSpecificAPI().value2java(arguments[i], false, false));
			if (this.packageContainer != null)
				return this.getSpecificAPI().getStruct(this.packageContainer.refCreateStruct(this.refMetaObject, javaArguments));
			else
				return this.getSpecificAPI().getStruct(this.classContainer.refCreateStruct(this.refMetaObject, javaArguments));
		} catch (Exception x) {
			return new MDRException(x.getMessage(), this.getSpecificAPI());
		}
    }

    public javax.jmi.model.StructureType getStructureType()
    {
		return this.refMetaObject;
    }

    public javax.jmi.model.StructureField[] getStructureFields()
    {
		if (this.fields == null) {
			java.util.List ret = new java.util.ArrayList(this.getStructureType().getContents());
	    	java.util.Iterator it = ret.iterator();
	    	Object o;
	    	while (it.hasNext()) {
	    		o = it.next();
	    		if (! (o instanceof StructureField))
	    			it.remove();
	    	}
	    	this.fields = (StructureField[])ret.toArray(new StructureField[ret.size()]);
    	}
		return this.fields;
    }

    public javax.jmi.model.StructureField getStructureField(
        String name)
    {
		StructureField[] fields = this.getStructureFields();
		for (int i = 0; i < fields.length; ++i)
			if (fields[i].getName().equals(name))
				return (fields[i]);
		return null;
    }

    public static String[] retreiveQualifiedName(
        javax.jmi.model.StructureType ref)
    {
		java.util.Collection qualifiedNameAsCollection = ref.getQualifiedName();
		String [] ret = new String [qualifiedNameAsCollection.size()];
		qualifiedNameAsCollection.toArray(ret);
		return ret;
    }
}
