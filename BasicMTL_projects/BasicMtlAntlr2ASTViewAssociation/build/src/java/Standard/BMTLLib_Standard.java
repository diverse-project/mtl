package Standard;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTLLib_Standard extends BMTLLibrary implements BMTLLib_StandardInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_StandardInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("Standard");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("OclType"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("ModelElement"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("BMTLObject"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("BMTLLibrary"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Void"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Real"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Integer"),new BMTLString("Real")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("String"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Boolean"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Iterator"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Collection"),new BMTLString("OclAny")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Set"),new BMTLString("Collection")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("OrderedSet"),new BMTLString("Collection")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Bag"),new BMTLString("Collection")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("Standard"),new BMTLString("Sequence"),new BMTLString("Collection")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclAnyInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclTypeInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLModelElementInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLObjectInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLLibInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLVoidInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLRealInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIntegerInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLStringInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBooleanInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIteratorInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLCollectionInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSetInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOrderedSetInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBagInterface;
public org.irisa.triskell.MT.DataTypes.Java.Type theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSequenceInterface;
static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("Standard");
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
    if (type[0].equals("OclAny")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclAnyInterface;
    if (type[0].equals("OclType")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclTypeInterface;
    if (type[0].equals("ModelElement")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLModelElementInterface;
    if (type[0].equals("BMTLObject")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLObjectInterface;
    if (type[0].equals("BMTLLibrary")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLLibInterface;
    if (type[0].equals("Void")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLVoidInterface;
    if (type[0].equals("Real")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLRealInterface;
    if (type[0].equals("Integer")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIntegerInterface;
    if (type[0].equals("String")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLStringInterface;
    if (type[0].equals("Boolean")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBooleanInterface;
    if (type[0].equals("Iterator")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIteratorInterface;
    if (type[0].equals("Collection")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLCollectionInterface;
    if (type[0].equals("Set")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSetInterface;
    if (type[0].equals("OrderedSet")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOrderedSetInterface;
    if (type[0].equals("Bag")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBagInterface;
    if (type[0].equals("Sequence")) return this.theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSequenceInterface;
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
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclAnyInterface = org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOclTypeInterface = org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLModelElementInterface = org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLObjectInterface = org.irisa.triskell.MT.BasicMTL.TopTypes.BasicMtlObjectType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eTopTypes_2eBMTLLibInterface = org.irisa.triskell.MT.BasicMTL.TopTypes.BasicMtlLibraryType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLVoidInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLRealInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIntegerInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLStringInterface = org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBooleanInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.BooleanType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLIteratorInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.IteratorType.TheInstance;
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLCollectionInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType.getCollectionType(org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance);
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSetInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetType.getSetType(org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance);
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLOrderedSetInterface = org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetType.getOrderedSetType(org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance);
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLBagInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Bag.BagType.getBagType(org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance);
theorg_2eirisa_2etriskell_2eMT_2eBasicMTL_2eDataTypes_2eBMTLSequenceInterface = org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceType.getSequenceType(org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType.TheInstance);
}

/* Direct constructor */
/*====================*/
public BMTLLib_Standard()
{ super("Standard");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("Standard",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_Standard(java.util.Hashtable map,BMTLLib_StandardInterface o)
{ super("Standard");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("Standard",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public Standard.BMTLLib_StandardInterface getRef_BMTLLib_Standard()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("Standard", BMTLLib_StandardInterface.class, BMTLLib_Standard.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_Standard TheInstance = new BMTLLib_Standard();
/*===================*/
/* INHERITED METHODS */
/*===================*/
}
