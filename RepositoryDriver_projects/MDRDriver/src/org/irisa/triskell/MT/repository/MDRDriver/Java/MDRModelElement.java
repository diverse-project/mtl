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
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
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
import org.apache.log4j.Logger;
import org.netbeans.api.mdr.CreationFailedException;
import javax.jmi.reflect.RefClass;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.CommonException;

public class MDRModelElement 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured
{
    private final javax.jmi.reflect.RefObject refObject;
    protected javax.jmi.reflect.RefObject getRefObject () {
        return this.refObject;
    }
    protected int cardRefObject () {
        if ( this.refObject == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass type;


    public MDRModelElement(
        boolean undefined,
        String error,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        javax.jmi.reflect.RefObject ref)
    {
        super(undefined, error, api, ref);
		this.refObject = ref;
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#delete() 
      */
    public void delete()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
		if (this.refObject != null) {
			this.refObject.refDelete();
			this.getSpecificAPI().removeElement(this);
		} else
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(this);

    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return (classifier instanceof MDRMetaClass) && this.refObject.refIsInstanceOf(((MDRMetaClass)classifier).getRefClass().refMetaObject(), true);
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isMetaObject() 
      */
    public boolean isMetaObject()
    {
		return false;
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isTypeOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return (classifier instanceof MDRMetaClass) && this.refObject.refIsInstanceOf(((MDRMetaClass)classifier).getRefClass().refMetaObject(), false);
    }

    public String toString()
    {
		return "model element " + this.getTheModelElement() + " instance of " + this.getType().toString();
    }

    public org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass getType()
    {
		if (this.type == null) {
			this.type = (MDRMetaClass)this.getSpecificAPI().getMetaClass((RefClass)this.refObject.refClass());
		}
		return this.type;
    }

    public javax.jmi.reflect.RefClass getRefClass()
    {
		return this.getRefObject().refClass();
    }
}
