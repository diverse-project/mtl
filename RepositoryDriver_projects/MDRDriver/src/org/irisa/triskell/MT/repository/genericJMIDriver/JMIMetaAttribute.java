/*
 * $Id: JMIMetaAttribute.java,v 1.1 2004-02-16 15:44:35 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaAttribute 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaAttribute
{

    public JMIMetaAttribute(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        String name,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass scope)
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

    protected org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		return new ExecutableJMIAttribute(this.getSpecificAPI(), self, (javax.jmi.model.Attribute)me);
    }
}
