package org.irisa.triskell.MT.repository.MDRDriver.Java;

import javax.jmi.model.GeneralizableElement;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

public class MDRMetaObject 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured
{
    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass metaClass;
    protected org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass getMetaClass () {
        return this.metaClass;
    }
    protected int cardMetaClass () {
        if ( this.metaClass == null ) return 0;
        else return 1;
    }


    public MDRMetaObject(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass metaClass)
    {
        super(false, null, metaClass.getSpecificAPI(), metaClass.getRefClass());
		this.metaClass = metaClass;
    }

    /**
      * (non-Javadoc)
      */
    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof MDRMetaObject) && (this.getMetaClass().equals(((MDRMetaObject)rhs).getMetaClass()));
    }

    /**
      * (non-Javadoc)
      * @see java.lang.Object#toString()
      */
    public String toString()
    {
		return this.getMetaClass().toString();
    }

    /**
      * (non-Javadoc)
      * 
      * 
      * 
      * 
      * 
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#delete() 
      */
    public void delete()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {


    }

    /**
      * (non-Javadoc)
      * 
      * 
      * 
      * 
      * 
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.isTypeOf(classifier) || (classifier == OclAnyType.TheInstance) || ((classifier instanceof MDRMetaClass) && ((GeneralizableElement)this.getRefClass().refMetaObject()).allSupertypes().contains(((MDRMetaClass)classifier).getRefClass().refMetaObject()));
    }

    /**
      * (non-Javadoc)
      * 
      * 
      * 
      * 
      * 
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isMetaObject() 
      */
    public boolean isMetaObject()
    {
		return true;
    }

    /**
      * (non-Javadoc)
      * 
      * 
      * 
      * 
      * 
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isTypeOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.getMetaClass().equals(classifier);
    }

    public javax.jmi.reflect.RefClass getRefClass()
    {
		return this.getMetaClass().getRefClass();
    }
}
