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

public class MDRMetaAttribute 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaAttribute
{

    public MDRMetaAttribute(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        String name,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass scope)
    {
        super(api, name, scope, null);
    }

    public String getKind()
    {
		return "attribute";
    }

    protected boolean checkKind(
        javax.jmi.model.ModelElement element)
    {
		return element instanceof javax.jmi.model.Attribute;
    }

    protected org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		return new ExecutableJMIAttribute(this.getSpecificAPI(), self, (javax.jmi.model.Attribute)me);
    }
}
