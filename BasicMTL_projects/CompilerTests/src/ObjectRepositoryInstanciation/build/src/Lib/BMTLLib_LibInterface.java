package Lib;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface BMTLLib_LibInterface extends BMTLLibInterface{
public Lib.BMTLLib_LibInterface getRef_BMTLLib_Lib();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public STL.BMTLLib_STLInterface get_BMTL_stl();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_stl (STL.BMTLLib_STLInterface value);
public org.irisa.triskell.MT.repository.API.Java.API get_BMTL_UML();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_UML (org.irisa.triskell.MT.repository.API.Java.API value);
public Lib.BMTLLib_LibInterface BMTL_init(org.irisa.triskell.MT.repository.API.Java.API BMTL_aModel) throws Throwable;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable;
}
