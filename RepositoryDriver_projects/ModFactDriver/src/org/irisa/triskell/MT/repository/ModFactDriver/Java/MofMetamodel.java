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

public class MofMetamodel 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.Metamodel
{
    protected static final org.irisa.triskell.MT.repository.ModFactDriver.Java.MofMetamodel TheInstance = new MofMetamodel();
    public static org.irisa.triskell.MT.repository.ModFactDriver.Java.MofMetamodel getTheInstance () {
        return MofMetamodel.TheInstance;
    }
    public static int cardTheInstance () {
        if ( MofMetamodel.TheInstance == null ) return 0;
        else return 1;
    }


    protected MofMetamodel()
    {
    }

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api)
        throws java.lang.Exception
    {
		api.getLog().debug("Instanciating MOF repository for model " + api.getModelName());
			return api.getModfactRepository().createExtent(api.getModelName());
    }
}
