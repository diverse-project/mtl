/*
 * Created on 4 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class MDRMetaType extends MDRMetaElement implements Type {


    public MDRMetaType(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
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
    		ret = new MDRConstrainedModelElementIterator(ret, constraint);
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
		return MDRAPI.toCollectionValue(this.allInstancesIterator(constraint), CollectionKind.set_kind, false);
    }
}
