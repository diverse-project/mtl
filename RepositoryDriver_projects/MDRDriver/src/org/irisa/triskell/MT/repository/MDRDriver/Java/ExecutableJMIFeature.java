package org.irisa.triskell.MT.repository.MDRDriver.Java;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class ExecutableJMIFeature 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature
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
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
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
