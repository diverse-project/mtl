package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
/*import org.netbeans.api.mdr.*;*/
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDRMetaOperation 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaOperation
{

    public MDRMetaOperation(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        String name,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaClass scope)
    {
        super(api, name, scope, null);
    }

    public String getKind()
    {
		return "operation";
    }

    public boolean checkArguments(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
		javax.jmi.model.Parameter [] opParams = getParameters((javax.jmi.model.Operation)element, new String [] {"in_dir", "inout_dir"});;
		return opParams.length == (arguments == null ? 0 : arguments.length);
    }

    protected boolean checkKind(
        javax.jmi.model.ModelElement element)
    {
		return element instanceof javax.jmi.model.Operation;
    }

    protected org.irisa.triskell.MT.repository.ModFactDriver.Java.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		return new ExecutableJMIOperation(this.getSpecificAPI(), self, arguments, (javax.jmi.model.Operation)me);
    }
}
