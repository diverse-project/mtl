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

public class MDRRole 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelRole
{
    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaAssociationEnd associationEnd;

    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured element;


    public MDRRole(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured element,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaAssociationEnd associationEnd)
    {
        super(element.getSpecificAPI(), null);
		this.element = element;
		this.associationEnd = associationEnd;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd getMetaAssociationEnd()
    {
		return this.associationEnd;
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement getModelElement()
    {
		return this.element;
    }

    public String toString()
    {
		return "role of " + this.getMetaAssociationEnd().toString() + " to " + this.getModelElement().toString();
    }
}
