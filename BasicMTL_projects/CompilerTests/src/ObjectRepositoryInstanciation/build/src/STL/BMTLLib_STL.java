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

public class BMTLLib_STL extends BMTLLibrary implements BMTLLib_STLInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_STLInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("STL");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("STL"),new BMTLString("Transformation")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fTransformation;
static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("STL");
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
    if (type[0].equals("Transformation")) return this.theBMTL_5fTransformation;
    if (type[0].equals("Standard")) return Standard.BMTLLib_Standard.myType;
    return null;
  }
  else {
    String [] unqualifiedType = new String [type.length-1];
    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);
    Type ret;
    ret = this.BMTLRef_BMTLLib_Standard.getMetaClass(unqualifiedType);
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
private void buildAllUsedLibs() {
BMTLRef_BMTLLib_Standard=Standard.BMTLLib_Standard.TheInstance;
}

public BMTLLibInterface getUsedLibrary(String libName) {
//Used libraries
if (libName.equals("BMTLLib_Standard")) return this.BMTLRef_BMTLLib_Standard;
//Inherited libraries
return this; } //current library by default

private void buildAllClassTypes() {
theBMTL_5fTransformation = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"STL", "Transformation"}, BMTL_TransformationInterface.class, BMTL_Transformation.class, null);
}

/* Direct constructor */
/*====================*/
public BMTLLib_STL()
{ super("STL");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("STL",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_STL(java.util.Hashtable map,BMTLLib_STLInterface o)
{ super("STL");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("STL",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public STL.BMTLLib_STLInterface getRef_BMTLLib_STL()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("STL", BMTLLib_STLInterface.class, BMTLLib_STL.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_STL TheInstance = new BMTLLib_STL();
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
try {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString("STL"),theCaller,BMTL_context));
}
catch (java.lang.Throwable throwable) { try {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;
} catch(Exception e) { throw throwable; }
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;
try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visitSTL",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});
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
