package STL;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTL_Transformation extends BMTLObject implements BMTL_TransformationInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Transformation oclAsType(Object o)
{     return (BMTL_Transformation)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_STLInterface theLib;
private BMTL_TransformationInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Transformation(STL.BMTLLib_STLInterface libRef)
{ super("Transformation");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("STL::Transformation",this);
theCaller=this;
theLib.recordNewInstance("Transformation",this);

}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Transformation(STL.BMTLLib_STLInterface libRef,java.util.Hashtable map,BMTL_TransformationInterface o)
{ super("Transformation");
theLib = (STL.BMTLLib_STLInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("STL::Transformation",this);
theLib.recordNewInstance("Transformation",theCaller);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    theLib.removeInstance("Transformation",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public STL.BMTL_Transformation getRef_BMTL_STL_5fTransformation()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Transformation"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_run(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
new BMTLString("->> Error : STL::Transformation::run() is an abstract operation").BMTL_toErr();
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(null);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_execute(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_result=null;
BMTL_result=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(theCaller.BMTL_run(BMTL_context));
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_result);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
try {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString("Transformation"),theCaller,BMTL_context));
}
catch (java.lang.Throwable throwable) { try {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;
} catch(Exception e) { throw throwable; }
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;
try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visitTransformation",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)m.invoke(BMTL_v,new Object[]{this,BMTL_context});
} catch (Exception e) {} BMTL_vFather=null;
BMTL_vFather=(DefaultVisitors.BMTL_CallableVisitorInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_getParent());
BooleanValue GenSymbol1 = (BooleanValue)theCaller.BMTL_isNull(BMTL_vFather).BMTL_not();
if (GenSymbol1.getTheBoolean()) {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(this.BMTL_accept(BMTL_vFather,BMTL_context));
} else {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
}
