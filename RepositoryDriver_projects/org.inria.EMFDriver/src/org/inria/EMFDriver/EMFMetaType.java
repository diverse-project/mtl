/* $Id: EMFMetaType.java,v 1.1 2004-03-08 08:18:16 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.repository.API.Java.LookupConstraint;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class EMFMetaType
	extends EMFMetaElement
	implements Type {

		public EMFMetaType(
			EMFAPI api,
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
				EMFDriver.log.warn("allInstancesIterator(LookupConstraint c) is not implemented => constraint(s) part ignored !");
			return ret;
		}
    

		/**
		  * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstances() 
		  */
		public CollectionValue allInstances()
			throws UnknownElementException
		{
			return EMFAPI.toCollectionValue(this.allInstancesIterator(null), CollectionKind.set_kind, false);
		}

		/**
		  * @see org.irisa.triskell.MT.repository.API.Java.MetaClass#allInstancesWithConstraint(org.irisa.triskell.MT.repository.API.Java.LookupConstraint) 
		  */
		public CollectionValue allInstancesWithConstraint(LookupConstraint constraint)
			throws UnknownElementException
		{
			EMFDriver.log.warn("allInstancesWithConstraint(LookupConstraint c) is not implemented => constraint(s) part ignored !");
			return this.allInstances();
		}

}
