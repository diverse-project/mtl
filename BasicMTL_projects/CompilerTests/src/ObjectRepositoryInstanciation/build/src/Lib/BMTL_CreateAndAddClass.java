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

public class BMTL_CreateAndAddClass extends BMTLObject implements BMTL_CreateAndAddClassInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_CreateAndAddClass oclAsType(Object o)
{     return (BMTL_CreateAndAddClass)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_LibInterface theLib;
private BMTL_CreateAndAddClassInterface theCaller;
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
public BMTL_CreateAndAddClass(Lib.BMTLLib_LibInterface libRef)
{ super("CreateAndAddClass");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("Lib::CreateAndAddClass",this);
theCaller=this;
theLib.recordNewInstance("CreateAndAddClass",this);

}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_CreateAndAddClass(Lib.BMTLLib_LibInterface libRef,java.util.Hashtable map,BMTL_CreateAndAddClassInterface o)
{ super("CreateAndAddClass");
theLib = (Lib.BMTLLib_LibInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("Lib::CreateAndAddClass",this);
theLib.recordNewInstance("CreateAndAddClass",theCaller);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    theLib.removeInstance("CreateAndAddClass",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public Lib.BMTL_CreateAndAddClass getRef_BMTL_Lib_5fCreateAndAddClass()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"CreateAndAddClass"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_run(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface BMTL_result=null;
BMTL_result=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.repository.API.Java.MetaClass)this.getLibrary().getMetaClass(new String[]{"UML","Core","Class"})).instanciate(null,null))));
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(null);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
try {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString("CreateAndAddClass"),theCaller,BMTL_context));
}
catch (java.lang.Throwable throwable) { try {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;
} catch(Exception e) { throw throwable; }
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;
try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visitCreateAndAddClass",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});
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
