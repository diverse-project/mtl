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

public class BMTLLib_Lib extends BMTLLibrary implements BMTLLib_LibInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_LibInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("Lib");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Lib"),new BMTLString("CreateAndAddClass")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
STL.BMTLLib_STLInterface BMTL_stl;

org.irisa.triskell.MT.repository.API.Java.API BMTL_UML;

public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fCreateAndAddClass;
static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("Lib");
  CompatibleNames = al;
}

public static final java.util.List CompatibleNames;
public org.irisa.triskell.MT.DataTypes.Java.Type getMetaClass(String [] type)
{ if (type == null || type.length == 0)
    return null;
  if (CompatibleNames.contains(type[0])) {
   if (type.length > 2)
     return null;
   else if (type.length == 2)
    return this.getMetaClass(new String [] {type[1]});
   else //if (type.length == 1)
    return this.getType();
  }
  if (type.length == 1) {
    if (type[0].equals("CreateAndAddClass")) return this.theBMTL_5fCreateAndAddClass;
    if (type[0].equals("Standard")) return Standard.BMTLLib_Standard.myType;
    if (type[0].equals("STL")) return STL.BMTLLib_STL.myType;
    return null;
  }
  else {
    String [] unqualifiedType = new String [type.length-1];
    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);
    Type ret;
    if (type[0].equals("stl")) {
      return this.BMTL_stl == null ? null : this.BMTL_stl.getMetaClass(unqualifiedType);
    }
    else if (type[0].equals("UML")) try {
      return this.BMTL_UML == null ? null : this.BMTL_UML.getMetaClass(unqualifiedType);
    } catch (UnknownElementException x) {}
    ret = this.BMTLRef_BMTLLib_Standard.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    ret = this.BMTLRef_BMTLLib_STL.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    return null;
  }
}

public BMTLLibInterface getLibrary() {
  return (BMTLLibrary)this.theCaller;
}

/*===========================*/
/* LIBRARYCLASS CONSTRUCTORS */
/*===========================*/
public Standard.BMTLLib_StandardInterface BMTLRef_BMTLLib_Standard;
public STL.BMTLLib_STLInterface BMTLRef_BMTLLib_STL;
private void buildAllUsedLibs() {
BMTLRef_BMTLLib_Standard=Standard.BMTLLib_Standard.TheInstance;
BMTLRef_BMTLLib_STL=STL.BMTLLib_STL.TheInstance;
}

public BMTLLibInterface getUsedLibrary(String libName) {
//Used libraries
if (libName.equals("BMTLLib_Standard")) return this.BMTLRef_BMTLLib_Standard;
if (libName.equals("BMTLLib_STL")) return this.BMTLRef_BMTLLib_STL;
//Inherited libraries
return this; } //current library by default

private void buildAllClassTypes() {
theBMTL_5fCreateAndAddClass = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"Lib", "CreateAndAddClass"}, BMTL_CreateAndAddClassInterface.class, BMTL_CreateAndAddClass.class, null);
}

/* Direct constructor */
/*====================*/
public BMTLLib_Lib()
{ super("Lib");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("Lib",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_Lib(java.util.Hashtable map,BMTLLib_LibInterface o)
{ super("Lib");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("Lib",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public Lib.BMTLLib_LibInterface getRef_BMTLLib_Lib()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("Lib", BMTLLib_LibInterface.class, BMTLLib_Lib.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_Lib TheInstance = new BMTLLib_Lib();
/*===================*/
/* INHERITED METHODS */
/*===================*/
public STL.BMTLLib_STLInterface get_BMTL_stl()
{ return this.BMTL_stl; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_stl (STL.BMTLLib_STLInterface value)
{ this.BMTL_stl=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.repository.API.Java.API get_BMTL_UML()
{ return this.BMTL_UML; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_UML (org.irisa.triskell.MT.repository.API.Java.API value)
{ this.BMTL_UML=value;
return BMTLVoid.TheInstance; }

public Lib.BMTLLib_LibInterface BMTL_init(org.irisa.triskell.MT.repository.API.Java.API BMTL_aModel) throws Throwable {
theCaller.set_BMTL_UML(BMTL_aModel);
return (Lib.BMTLLib_LibInterface)CommonFunctions.toBMTLDataType(theCaller);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
try {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString("Lib"),theCaller,BMTL_context));
}
catch (java.lang.Throwable throwable) { try {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;
} catch(Exception e) { throw throwable; }
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;
try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visitLib",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});
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
