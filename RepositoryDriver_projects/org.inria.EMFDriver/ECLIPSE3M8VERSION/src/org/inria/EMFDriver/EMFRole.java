/*
 * $Id: EMFRole.java,v 1.1 2004-04-28 10:20:48 jpthibau Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class EMFRole 
    extends EMFElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelRole
{
    private final EMFMetaAssociationEnd associationEnd;

    private final EMFFeatured element;


    public EMFRole(
        EMFFeatured element,
        EMFMetaAssociationEnd associationEnd)
    {
        super(element.getSpecificAPI(), null);
		this.element = element;
		this.associationEnd = associationEnd;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd getMetaAssociationEnd()
    {
		return this.associationEnd;
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement getModelElement()
    {
		return this.element;
    }

    public String toString()
    {
		return "role of " + this.getMetaAssociationEnd().toString() + " to " + this.getModelElement().toString();
    }
}
