/*
 * $Id: ExecutableJMIAssociationEnd.java,v 1.1 2004-10-25 12:32:45 dvojtise Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import java.util.Iterator;

import javax.jmi.model.MofClass;
import javax.jmi.model.Reference;

import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * Object to be used with the genric JMI implementation of the repository API
 */
public class ExecutableJMIAssociationEnd 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature
{
    protected javax.jmi.model.AssociationEnd associationEnd;
    public javax.jmi.model.AssociationEnd getAssociationEnd () {
        return this.associationEnd;
    }
    public int cardAssociationEnd () {
        if ( this.associationEnd == null ) return 0;
        else return 1;
    }


    public ExecutableJMIAssociationEnd(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        javax.jmi.model.AssociationEnd associationEnd)
    {
        super(api, self, null);
		this.associationEnd = associationEnd;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value execute()
        throws java.lang.Exception
    {
    	this.ensureAssociationEndIgnore(null);
		boolean isOrdered = this.getAssociationEnd().getMultiplicity().isOrdered();
		boolean isUnique =  this.getAssociationEnd().getMultiplicity().isUnique();
		int maxMultiplicity = this.getAssociationEnd().getMultiplicity().getUpper();
		javax.jmi.model.Association mass = (javax.jmi.model.Association)(this.getAssociationEnd()).getContainer();
//		java.util.LinkedList mpacks = new java.util.LinkedList();
//		javax.jmi.model.MofPackage mpack = (javax.jmi.model.MofPackage)mass.getContainer();
//		do {
//			mpacks.addFirst(mpack);
//			mpack = (javax.jmi.model.MofPackage)mpack.getContainer();
//		} while (mpack != null);
//		javax.jmi.reflect.RefPackage pack = this.getApi().getModel();
//		java.util.Iterator mpacksIt = mpacks.iterator();
//		if (pack.refMetaObject().equals(mpacks.getFirst()))
//			mpacksIt.next();
//		while (mpacksIt.hasNext())
//			pack = pack.refPackage(((javax.jmi.model.MofPackage)mpacksIt.next()));
//		javax.jmi.reflect.RefAssociation ass = pack.refAssociation(mass);
		javax.jmi.reflect.RefAssociation ass = this.getApi().findAssociationFromMetaObject(mass);
		
		Object res = ass.refQuery(this.getAssociationEnd().otherEnd(), (javax.jmi.reflect.RefObject)this.getSelf().getRefFeatured());
		if (maxMultiplicity == 1) {
			if (((java.util.Collection)res).size() > 1)
				throw new RuntimeException("Internal error.", new Exception("Invalid return multiplicity while calling link."));
			res = ((java.util.Collection)res).size() == 0 ? null : ((java.util.Collection)res).iterator().next();
		}
		return this.getApi().java2value(res, isOrdered, isUnique, false);
    }
    
    protected void ensureAssociationEndIgnore (ModelElement contextualElement) throws org.irisa.triskell.MT.repository.API.Java.IllegalAccessException {
        if (System.getProperty(JMIAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true")) {
        	MofClass selfClazz = (MofClass)this.getSelf().getRefClass().refMetaObject();
        	Iterator contentsIt = selfClazz.getContents().iterator();
        	Object o;
        	while (contentsIt.hasNext()) {
        		o = contentsIt.next();
        		if ((o instanceof Reference) && ((Reference)o).getReferencedEnd().equals(this.getAssociationEnd()))
        			return;
        	}
        	throw new org.irisa.triskell.MT.repository.API.Java.IllegalAccessException (contextualElement, (MetaAssociationEnd)this.getSelf().getSpecificAPI().getCachedMetaElement(this.getAssociationEnd()));
        }
    }
}
