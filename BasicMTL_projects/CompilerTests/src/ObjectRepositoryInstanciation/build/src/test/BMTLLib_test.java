package test;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTLLib_test extends BMTLLibrary implements BMTLLib_testInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_testInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("test");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.repository.API.Java.API BMTL_source_5fmodel;

Lib.BMTLLib_LibInterface BMTL_uml;

STL.BMTLLib_STLInterface BMTL_stl;

static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("test");
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
    if (type[0].equals("Standard")) return Standard.BMTLLib_Standard.myType;
    if (type[0].equals("Lib")) return Lib.BMTLLib_Lib.myType;
    if (type[0].equals("STL")) return STL.BMTLLib_STL.myType;
    if (type[0].equals("MDRDriver")) return MDRDriver.BMTLLib_MDRDriver.myType;
    return null;
  }
  else {
    String [] unqualifiedType = new String [type.length-1];
    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);
    Type ret;
    if (type[0].equals("source_model")) try {
      return this.BMTL_source_5fmodel == null ? null : this.BMTL_source_5fmodel.getMetaClass(unqualifiedType);
    } catch (UnknownElementException x) {}
    else if (type[0].equals("uml")) {
      return this.BMTL_uml == null ? null : this.BMTL_uml.getMetaClass(unqualifiedType);
    }
    else if (type[0].equals("stl")) {
      return this.BMTL_stl == null ? null : this.BMTL_stl.getMetaClass(unqualifiedType);
    }
    ret = this.BMTLRef_BMTLLib_Standard.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    ret = this.BMTLRef_BMTLLib_Lib.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    ret = this.BMTLRef_BMTLLib_STL.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    ret = this.BMTLRef_BMTLLib_MDRDriver.getMetaClass(unqualifiedType);
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
public Lib.BMTLLib_LibInterface BMTLRef_BMTLLib_Lib;
public STL.BMTLLib_STLInterface BMTLRef_BMTLLib_STL;
public MDRDriver.BMTLLib_MDRDriverInterface BMTLRef_BMTLLib_MDRDriver;
private void buildAllUsedLibs() {
BMTLRef_BMTLLib_Standard=Standard.BMTLLib_Standard.TheInstance;
BMTLRef_BMTLLib_Lib=Lib.BMTLLib_Lib.TheInstance;
BMTLRef_BMTLLib_STL=STL.BMTLLib_STL.TheInstance;
BMTLRef_BMTLLib_MDRDriver=MDRDriver.BMTLLib_MDRDriver.TheInstance;
}

public BMTLLibInterface getUsedLibrary(String libName) {
//Used libraries
if (libName.equals("BMTLLib_Standard")) return this.BMTLRef_BMTLLib_Standard;
if (libName.equals("BMTLLib_Lib")) return this.BMTLRef_BMTLLib_Lib;
if (libName.equals("BMTLLib_STL")) return this.BMTLRef_BMTLLib_STL;
if (libName.equals("BMTLLib_MDRDriver")) return this.BMTLRef_BMTLLib_MDRDriver;
//Inherited libraries
return this; } //current library by default

private void buildAllClassTypes() {
}

/* Direct constructor */
/*====================*/
public BMTLLib_test()
{ super("test");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("test",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_test(java.util.Hashtable map,BMTLLib_testInterface o)
{ super("test");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("test",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public test.BMTLLib_testInterface getRef_BMTLLib_test()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("test", BMTLLib_testInterface.class, BMTLLib_test.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_test TheInstance = new BMTLLib_test();
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.repository.API.Java.API get_BMTL_source_5fmodel()
{ return this.BMTL_source_5fmodel; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_source_5fmodel (org.irisa.triskell.MT.repository.API.Java.API value)
{ this.BMTL_source_5fmodel=value;
return BMTLVoid.TheInstance; }

public Lib.BMTLLib_LibInterface get_BMTL_uml()
{ return this.BMTL_uml; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_uml (Lib.BMTLLib_LibInterface value)
{ this.BMTL_uml=value;
return BMTLVoid.TheInstance; }

public STL.BMTLLib_STLInterface get_BMTL_stl()
{ return this.BMTL_stl; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_stl (STL.BMTLLib_STLInterface value)
{ this.BMTL_stl=value;
return BMTLVoid.TheInstance; }

public static void main(String args[]) {
  try {
    BMTLLib_test.TheInstance.BMTL_main();
    System.exit(0);
  } catch (Throwable t) {
    System.err.println("Problem while executing the main function of library test:");
    t.printStackTrace();
    System.exit(-1);
  }
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_main() throws Throwable {
MDRDriver.MDRModelManager BMTL_mdrdriver=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_metamodelFilename=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_inputFilename=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_outputFilename=null;
Lib.BMTL_CreateAndAddClassInterface BMTL_cmd=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_cIte=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface BMTL_aClass=null;
BMTL_metamodelFilename=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(new BMTLString("MetaModel/UML_metamodel.xmi"));
BMTL_inputFilename=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(new BMTLString("Model/essai-in.xmi"));
BMTL_outputFilename=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(new BMTLString("Model/essai-out.xmi"));
BMTL_mdrdriver=(MDRDriver.MDRModelManager)CommonFunctions.toBMTLDataType(((MDRDriver.MDRModelManager)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"MDRDriver", "MDRModelManager"})).instanciate())));
BMTL_mdrdriver.BMTL_init();
theCaller.set_BMTL_source_5fmodel(BMTL_mdrdriver.BMTL_getModelFromXMI(BMTL_metamodelFilename,new BMTLString("UML"),new BMTLString("UML1.4_model"),BMTL_inputFilename,BMTL_outputFilename));
theCaller.set_BMTL_uml(((Lib.BMTLLib_LibInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Lib"})).instanciate())));
theCaller.get_BMTL_uml().BMTL_init(theCaller.get_BMTL_source_5fmodel());
new BMTLString("Debut de la transformation ...").BMTL_toOut();
BMTL_cmd=(Lib.BMTL_CreateAndAddClassInterface)CommonFunctions.toBMTLDataType(((Lib.BMTL_CreateAndAddClassInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Lib", "CreateAndAddClass"})).instanciate())));
BMTL_cmd.BMTL_run(null);
new BMTLString("That s all folks !").BMTL_toOut();
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_CallableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
try {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visit(new BMTLString("test"),theCaller,BMTL_context));
}
catch (java.lang.Throwable throwable) { try {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_w=(DefaultVisitors.BMTL_CallableVisitorInterface)throwable;
} catch(Exception e) { throw throwable; }
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(BMTL_v.BMTL_visitOclAny(theCaller,BMTL_context));
}
}
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_accept(DefaultVisitors.BMTL_InvokableVisitorInterface BMTL_v,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_context) throws Throwable {
DefaultVisitors.BMTL_CallableVisitorInterface BMTL_vFather=null;
try { java.lang.reflect.Method m = BMTL_v.getClass().getMethod("BMTL_visittest",new Class[]{org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface.class});
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
