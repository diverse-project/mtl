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

abstract public class MDRElement 
    implements org.irisa.triskell.MT.repository.API.Java.Element
{
    protected org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api;

    protected final java.lang.Object ref;
    public java.lang.Object getRef () {
        return this.ref;
    }
    public int cardRef () {
        if ( this.ref == null ) return 0;
        else return 1;
    }


    public MDRElement(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        java.lang.Object ref)
    {
		this.ref = ref;
		this.api = api;
		if (ref != null)
			api.setElement(this, ref);
    }

    public org.irisa.triskell.MT.repository.API.Java.API getAPI()
    {
		return this.api;
    }

    public org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI getSpecificAPI()
    {
		return this.api;
    }

    public abstract String toString();
}
