package ASTJava;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTLLib_ASTJava extends BMTLLibrary implements BMTLLib_ASTJavaInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_ASTJavaInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("ASTJava");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Object")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Library")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("UserClass")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Attribute")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Operation")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("While")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("BooleanLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("IntLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("RealLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("StringLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("NullLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("OclTypeLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("SelfLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("JavaCodeLiteral")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("ASTJava"),new BMTLString("Property")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("ASTJava");
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
    return null;
  }
  else {
    return null;
  }
}

public BMTLLibInterface getLibrary() {
  return (BMTLLibrary)this.theCaller;
}

/*===========================*/
/* LIBRARYCLASS CONSTRUCTORS */
/*===========================*/
private void buildAllUsedLibs() {
}

private void buildAllClassTypes() {
}

/* Direct constructor */
/*====================*/
public BMTLLib_ASTJava()
{ super("ASTJava");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("ASTJava",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_ASTJava(java.util.Hashtable map,BMTLLib_ASTJavaInterface o)
{ super("ASTJava");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("ASTJava",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public ASTJava.BMTLLib_ASTJavaInterface getRef_BMTLLib_ASTJava()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("ASTJava", BMTLLib_ASTJavaInterface.class, BMTLLib_ASTJava.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_ASTJava TheInstance = new BMTLLib_ASTJava();
/*===================*/
/* INHERITED METHODS */
/*===================*/
}
