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

public class ExecutableJMIOperation 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature
{
    protected javax.jmi.model.Operation operation;
    public javax.jmi.model.Operation getOperation () {
        return this.operation;
    }
    public int cardOperation () {
        if ( this.operation == null ) return 0;
        else return 1;
    }


    public ExecutableJMIOperation(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.Operation operation)
    {
        super(api, self, arguments);
		this.operation = operation;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value execute()
        throws java.lang.Exception
    {
		java.util.List javaArguments = adaptArguments(this.getOperation(), arguments, this.getApi());
		Object ret = this.getSelf().getRefFeatured().refInvokeOperation(this.getOperation(), javaArguments);
		return adaptResults(this.getOperation(), javaArguments, ret, this.getApi());
    }
}
