/*
 * $Id: JMIMetaOperation.java,v 1.1 2004-10-25 12:32:56 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaOperation 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaOperation
{

    public JMIMetaOperation(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        String name,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass scope)
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

    protected org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		return new ExecutableJMIOperation(this.getSpecificAPI(), self, arguments, (javax.jmi.model.Operation)me);
    }
}
