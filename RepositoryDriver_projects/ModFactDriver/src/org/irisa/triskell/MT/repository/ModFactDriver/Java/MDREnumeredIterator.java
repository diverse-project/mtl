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

public class MDREnumeredIterator 
    implements org.irisa.triskell.MT.repository.API.Java.ModelElementIterator
{
    private final org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaEnumeration enumeration;

    private int index = 0;


    public MDREnumeredIterator(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaEnumeration enumeration)
    {
		this.enumeration = enumeration;
    }

    public int size()
    {
		return this.enumeration.enumered.length;
    }

    public boolean hasNext()
    {
		return this.index < this.size();
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement next()
        throws NoMoreElementException
    {
		return this.enumeration.enumered[this.index++];
    }

    public void reset()
    {
		this.index = 0;

    }
}
