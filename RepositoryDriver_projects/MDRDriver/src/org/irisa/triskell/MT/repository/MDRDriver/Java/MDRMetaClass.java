package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.Set;

import org.irisa.triskell.MT.DataTypes.Java.commands.Type;

public class MDRMetaClass 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaClass
{
    private javax.jmi.reflect.RefClass refClass;

    private org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaObject metaObject;


    public MDRMetaClass(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.reflect.RefClass ref)
    {
		this(api, ref, retreiveQualifiedName(ref));
    }

    public MDRMetaClass(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.reflect.RefClass ref,
        String[] qualifiedName)
    {
        super(api, ref, qualifiedName == null ? (String)ref.refGetValue("name") : qualifiedName[qualifiedName.length - 1], qualifiedName);
		this.refClass = ref;
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstances() 
      */
    public org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstances()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		return new MDRModelElementIterator(this.getSpecificAPI(), this.getRefClass().refAllOfType());
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstancesWithConstraint(org.irisa.triskell.MT.repository.API.Java.LookupConstraint) 
      */
    public org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstancesWithConstraint(
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		return new MDRConstrainedModelElementIterator(this.allInstances(), constraint);
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#getMetaObject() 
      */
    public org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject()
    {
		if (this.metaObject == null)
			this.metaObject = new MDRMetaObject(this);
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
		} catch (Exception x) {
			throw new org.irisa.triskell.MT.repository.API.Java.CommonException(x.getMessage());
		}
		if (ret == null)
			throw new org.irisa.triskell.MT.repository.API.Java.CommonException(this.toString() + ": construction failed (unknown reason).");
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
}
