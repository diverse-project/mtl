/*
 * $Id: JMIMetaType.java,v 1.1 2004-02-16 15:44:32 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public abstract class JMIMetaType extends JMIMetaElement implements Type {


    public JMIMetaType(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        java.lang.Object ref,
        String name,
        String[] qualifiedName)
    {
        super(api, ref, name, qualifiedName);
    }
    
    /**
     * Returns an iterator over all instances of the defined meta type. No constraint on defined types.
     * @return ModelElementIterator
     */
    public abstract ModelElementIterator allInstancesIterator ();
    
    /**
     * Returns an iterator over all instances of the defined meta type.
     * @param constraint none if null
     * @return ModelElementIterator
     */
    public ModelElementIterator allInstancesIterator (LookupConstraint constraint) {
    	ModelElementIterator ret = this.allInstancesIterator();
    	if (constraint != null)
    		ret = new JMIConstrainedModelElementIterator(ret, constraint);
    	return ret;
    }
    

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstances() 
      */
    public CollectionValue allInstances()
        throws UnknownElementException
    {
		return this.allInstancesWithConstraint(null);
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstancesWithConstraint(org.irisa.triskell.MT.repository.API.Java.LookupConstraint) 
      */
    public CollectionValue allInstancesWithConstraint(LookupConstraint constraint)
        throws UnknownElementException
    {
		return JMIAPI.toCollectionValue(this.allInstancesIterator(constraint), CollectionKind.set_kind, false);
    }
}
