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

public class MDRUnknownElement 
    implements org.irisa.triskell.MT.repository.API.Java.Element
{
    private String description;

    private org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api;


    public MDRUnknownElement(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        String description)
    {
		this.api = api;
		this.description = description;
    }

    public org.irisa.triskell.MT.repository.API.Java.API getAPI()
    {
		return this.api;
    }

    public String toString()
    {
		return this.description;
    }
}
