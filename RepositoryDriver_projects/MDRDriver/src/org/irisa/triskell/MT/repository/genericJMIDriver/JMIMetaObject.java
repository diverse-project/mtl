/*
 * $Id: JMIMetaObject.java,v 1.2 2004-07-30 13:20:13 ffondeme Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

// import javax.jmi.model.GeneralizableElement;
import javax.jmi.reflect.RefClass;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
// import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
// import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType;
// import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
// import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
// import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaObject 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured
    implements TypeValue
{
    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass metaClass;
    protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass getMetaClass () {
        return this.metaClass;
    }
    protected int cardMetaClass () {
        if ( this.metaClass == null ) return 0;
        else return 1;
    }


    public JMIMetaObject(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass metaClass)
    {
        super(false, null, metaClass.getSpecificAPI(), metaClass.getRefClass());
		this.metaClass = metaClass;
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof JMIMetaObject) && (this.getMetaClass().equals(((JMIMetaObject)rhs).getMetaClass()));
    }

    public String toString()
    {
		return this.getMetaClass().toString();
    }

    public void delete() {}
	
	public void deleteTheModelElement () {
		this.delete();
	}
    
    public boolean isKindOf(MetaClass classifier)
    {
		return this.getMetaClass().conformsTo(classifier);
    }

    public boolean isMetaObject()
    {
		return true;
    }

    public boolean isTypeOf(MetaClass classifier)
    {
		return this.getMetaClass().equals(classifier);
    }

    public RefClass getRefClass()
    {
		return this.getMetaClass().getRefClass();
    }

	public Type getType() {
		return OclTypeType.TheInstance;
	}

	protected CommandGroup getBaseCommandGroup() {
		return OclTypeCommandGroup.TheInstance;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.TypeValue#getTheType()
	 */
	public Type getTheType() {
		return this.getMetaClass(); 
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue#getValue()
	 */
	public String getValue() {
		return this.getMetaClass().getQualifiedNameAsString();
	}

	protected void cache() {
	}

}
