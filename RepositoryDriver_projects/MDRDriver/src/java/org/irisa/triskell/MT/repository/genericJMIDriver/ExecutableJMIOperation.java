/*
 * $Id: ExecutableJMIOperation.java,v 1.1 2004-10-25 12:32:52 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Object to be used with the genric JMI implementation of the repository API
 */
public class ExecutableJMIOperation 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature
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
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
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
