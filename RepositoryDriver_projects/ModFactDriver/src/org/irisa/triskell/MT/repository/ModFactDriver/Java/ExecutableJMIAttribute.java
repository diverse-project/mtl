package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
// import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class ExecutableJMIAttribute 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.ExecutableJMIFeature
{

    public ExecutableJMIAttribute(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self,
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
