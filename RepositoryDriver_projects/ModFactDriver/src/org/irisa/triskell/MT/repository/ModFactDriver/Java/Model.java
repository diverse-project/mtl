package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
/*import org.netbeans.api.mdr.*;*/
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

abstract public class Model 
{

    public abstract void load(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api)
        throws java.lang.Exception;

    public abstract void store(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api)
        throws java.lang.Exception;
}
