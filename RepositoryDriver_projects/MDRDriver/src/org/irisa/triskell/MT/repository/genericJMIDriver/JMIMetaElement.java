/**
 * $Id: JMIMetaElement.java,v 1.2 2004-07-30 13:20:12 ffondeme Exp $
 * author : dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
abstract public class JMIMetaElement 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaElement
{
    private final String name;

    private final String[] qualifiedName;
    
    private transient String qualifiedNameAsString = null;


    public JMIMetaElement(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        java.lang.Object ref,
        String name,
        String[] qualifiedName)
    {
        super(api, ref);
		this.name = name == null ? (qualifiedName == null ? null : qualifiedName[qualifiedName.length-1]) : name;
		this.qualifiedName = qualifiedName;
    }

    public String getName()
    {
		return this.name;
    }

    public String[] getQualifiedName()
    {
		return this.qualifiedName;
    }

    public String getQualifiedNameAsString()
    {
    	if (this.qualifiedNameAsString == null)
    		this.qualifiedNameAsString = AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
		return this.qualifiedNameAsString;
    }
    
	protected void cache() {
		this.getSpecificAPI().setCachedMetaElement(this);
	}
}
