/* $Id: EMFAPI.java,v 1.1 2004-02-27 08:30:53 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelRole;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFAPI
implements org.irisa.triskell.MT.repository.API.Java.API
{
		/* (non-Javadoc)
		 * @see org.irisa.triskell.MT.repository.API.Java.API#startup(org.irisa.triskell.MT.DataTypes.Java.Value[])
		 */
		public void startup(Value[] arg0) {
			// TODO Auto-generated method stub

		}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#shutdown(org.irisa.triskell.MT.DataTypes.Java.Value[])
	 */
	public void shutdown(Value[] arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaClass(java.lang.String[])
	 */
	public MetaClass getMetaClass(String[] arg0)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAttribute(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAttribute getMetaAttribute(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaFeature(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaFeature getMetaFeature(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaOperation(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaOperation getMetaOperation(String arg0, MetaClass arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociation(java.lang.String[])
	 */
	public MetaAssociation getMetaAssociation(String[] arg0)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationWithAssociationEnds(org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd[])
 	*/
	public MetaAssociation getMetaAssociationWithAssociationEnds(MetaAssociationEnd[] arg0)
		throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationEnd(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAssociationEnd getMetaAssociationEnd(
		String arg0,
		MetaClass arg1,
		MetaClass arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getRole(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd)
 	*/
	public ModelRole getRole(ModelElement arg0, MetaAssociationEnd arg1) {
		// TODO Auto-generated method stub
		return null;
}


}
