/*
 * $Id: JMIRole.java,v 1.1 2004-02-16 15:44:37 dvojtise Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIRole 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelRole
{
    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaAssociationEnd associationEnd;

    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured element;


    public JMIRole(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured element,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaAssociationEnd associationEnd)
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
