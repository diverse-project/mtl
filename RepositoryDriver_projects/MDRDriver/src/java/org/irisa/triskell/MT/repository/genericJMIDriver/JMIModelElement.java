/*
 * $Id: JMIModelElement.java,v 1.1 2004-10-25 12:32:53 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
 package org.irisa.triskell.MT.repository.genericJMIDriver;

import javax.jmi.reflect.RefClass;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementCommandGroup;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIModelElement 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured
{
    private final javax.jmi.reflect.RefObject refObject;
    protected javax.jmi.reflect.RefObject getRefObject () {
        return this.refObject;
    }
    protected int cardRefObject () {
        if ( this.refObject == null ) return 0;
        else return 1;
    }

    protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass type;


    public JMIModelElement(
        boolean undefined,
        String error,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
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
			this.getSpecificAPI().removeCachedModelElement(this);
		} else
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(this);

    }
	
	public void deleteTheModelElement () throws Exception {
		this.delete();
	}

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return (classifier instanceof JMIMetaClass) && this.refObject.refIsInstanceOf(((JMIMetaClass)classifier).getRefClass().refMetaObject(), true);
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
		return (classifier instanceof JMIMetaClass) && this.refObject.refIsInstanceOf(((JMIMetaClass)classifier).getRefClass().refMetaObject(), false);
    }

    public String toString()
    {
		return "model element #" + this.getTheModelElement() + " : " + this.getType().toString();
    }

    public Type getType()
    {
		if (this.type == null) {
			this.type = (JMIMetaClass)this.getSpecificAPI().getMetaClass((RefClass)this.refObject.refClass());
		}
		return this.type;
    }

    public javax.jmi.reflect.RefClass getRefClass()
    {
		return this.getRefObject().refClass();
    }

	protected CommandGroup getBaseCommandGroup() {
		return ModelElementCommandGroup.TheInstance;
	}

	protected void cache() {
		this.getSpecificAPI().setCachedModelElement(this);
	}

}
