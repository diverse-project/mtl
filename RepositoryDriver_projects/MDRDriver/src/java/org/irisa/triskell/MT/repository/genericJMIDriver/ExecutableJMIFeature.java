/*
 * $Id: ExecutableJMIFeature.java,v 1.1 2004-10-25 12:32:46 dvojtise Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Object to be used with the genric JMI implementation of the repository API
 */
public class ExecutableJMIFeature 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature
{
    protected javax.jmi.model.Feature feature;
    public javax.jmi.model.Feature getFeature () {
        return this.feature;
    }
    public int cardFeature () {
        if ( this.feature == null ) return 0;
        else return 1;
    }


    public ExecutableJMIFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        javax.jmi.model.Feature feature)
    {
        super(api, self, null);
		this.feature = feature;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value execute()
        throws java.lang.Exception
    {
    	boolean isOrdered = false, isUnique = false;
		if (this.getFeature() instanceof javax.jmi.model.StructuralFeature) {
			isOrdered = ((javax.jmi.model.StructuralFeature)this.getFeature()).getMultiplicity().isOrdered();
			isUnique =  ((javax.jmi.model.StructuralFeature)this.getFeature()).getMultiplicity().isUnique();
		}
		return this.getApi().java2value(this.getSelf().getRefFeatured().refGetValue(this.getFeature()), isOrdered, isUnique, false);
    }
}
