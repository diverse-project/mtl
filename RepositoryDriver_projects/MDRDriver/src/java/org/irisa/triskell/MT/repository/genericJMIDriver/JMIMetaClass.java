/*
 * $Id: JMIMetaClass.java,v 1.1 2004-10-25 12:32:54 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import java.util.ArrayList;
import java.util.Iterator;

import javax.jmi.model.MofClass;
import javax.jmi.reflect.AlreadyExistsException;

// import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
// import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
// import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
// import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
// import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaClass 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaType
    implements org.irisa.triskell.MT.repository.API.Java.MetaClass
{
    private javax.jmi.reflect.RefClass refClass;

    private org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaObject metaObject;


    public JMIMetaClass(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        javax.jmi.reflect.RefClass ref)
    {
		this(api, ref, retreiveQualifiedName(ref));
    }

    public JMIMetaClass(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        javax.jmi.reflect.RefClass ref,
        String[] qualifiedName)
    {
        super(api, ref, qualifiedName == null ? (String)ref.refGetValue("name") : qualifiedName[qualifiedName.length - 1], qualifiedName);
		this.refClass = ref;
    }
    
    public ModelElementIterator allInstancesIterator () {
    	return new JMIModelElementIterator(this.getSpecificAPI(), this.getRefClass().refAllOfType());
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#getMetaObject() 
      */
    public org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject()
    {
		if (this.metaObject == null)
			this.metaObject = new JMIMetaObject(this);
		return this.metaObject;
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#instanciate(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.DataTypes.Java.Value[]) 
      */
    public org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
    {
		//Every constructor is supposed to be public
		//Every constructor parameter is supposed to be of type IN_DIR.
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
		try {
			java.util.List javaArguments = new java.util.ArrayList(arguments == null ? 0 : arguments.length);
			for (int i = 0; i < (arguments == null ? 0 : arguments.length); ++i)
				javaArguments.add(this.getSpecificAPI().value2java(arguments[i], false, false));
			ret = this.getSpecificAPI().getModelElement(this.refClass.refCreateInstance(arguments == null ? null : javaArguments));
		} catch (AlreadyExistsException x) {
			if (((MofClass)this.getRefClass().refMetaObject()).isSingleton())
				throw new CommonException("Cannot build a second value of singleton " + this.toString());
			else
				throw new CommonException(x.getMessage());
		} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}
		if (ret == null)
			throw new CommonException(this.toString() + ": construction failed (unknown reason).");
		return ret;
    }

    public static String[] retreiveQualifiedName(
        javax.jmi.reflect.RefClass ref)
    {
		java.util.Collection qualifiedNameAsCollection = (java.util.Collection)ref.refMetaObject().refGetValue("qualifiedName");
		String [] ret = new String [qualifiedNameAsCollection.size()];
		qualifiedNameAsCollection.toArray(ret);
		return ret;
    }

    public String toString()
    {
		return "meta class " + org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
    }

    public javax.jmi.reflect.RefClass getRefClass()
    {
		return this.refClass;
    }

	public boolean conformsTo(Type parentType) {
		if (this.equals(parentType))
			return true;
		if (ModelElementType.TheInstance.conformsTo(parentType))
			return true;
		if (! (parentType instanceof JMIMetaClass))
			return false;
		JMIMetaClass parentClass = (JMIMetaClass)parentType;
		if (! this.getSpecificAPI().equals(parentClass.getSpecificAPI()))
			return false;
			 
		MofClass parentMOFClass = (MofClass)parentClass.getRefClass().refMetaObject();
		ArrayList conforming = new ArrayList(1);
		conforming.add((MofClass)this.getRefClass());
		Iterator it;
		do {
			it = conforming.iterator();
			while (it.hasNext()) {
				if (it.next().equals(parentMOFClass))
					return true;
			}
			
			it = conforming.iterator();
			conforming = new ArrayList();
			while (it.hasNext())
				conforming.addAll(((MofClass)it.next()).getSupertypes());
		} while (! conforming.isEmpty());
		return false;
	}

	public boolean isKindOf(Value v) {
		return (v instanceof JMIModelElement) && ((JMIModelElement)v).isKindOf(this);
	}

	public boolean isTypeOf(Value v) {
		return (v instanceof JMIModelElement) && ((JMIModelElement)v).isTypeOf(this);
	}

}
