/*
 * $Id: ExecutableJMIAttribute.java,v 1.1 2004-02-16 15:44:33 dvojtise Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Object to be used with the genric JMI implementation of the repository API
 */
public class ExecutableJMIAttribute 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableJMIFeature
{

    public ExecutableJMIAttribute(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        javax.jmi.model.Attribute attribute)
    {
        super(api, self, attribute);
    }

    public javax.jmi.model.Attribute getAttribute()
    {
		return (javax.jmi.model.Attribute)this.getFeature();
    }

    public void setValue(
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws java.lang.Exception
    {
		this.getSelf().getRefFeatured().refSetValue(this.getAttribute(), this.getApi().value2java(value, false, this.getAttribute().getMultiplicity().getUpper() > 1));
    }
}
