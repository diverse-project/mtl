package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.utils.Java.AWK;

abstract public class MDRMetaElement 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaElement
{
    private final String name;

    private final String[] qualifiedName;
    
    private transient String qualifiedNameAsString = null;


    public MDRMetaElement(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
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
}
