package BasicMtlASTView;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTL_BasicMtlLibrary extends BMTLObject implements BMTL_BasicMtlLibraryInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_BasicMtlLibrary oclAsType(Object o)
{     return (BMTL_BasicMtlLibrary)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_BasicMtlLibraryInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Library BMTLRef_BMTL_Library;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_parameters;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_BasicMtlLibrary(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("BasicMtlLibrary");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::BasicMtlLibrary",this);
theCaller=this;
theLib.recordNewInstance("BasicMtlLibrary",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Library")) 
	BMTLRef_BMTL_Library= (BMTL_Library)inheritanceMap.get("BasicMtlASTView::Library");
else BMTLRef_BMTL_Library= new BMTL_Library(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_BasicMtlLibrary(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_BasicMtlLibraryInterface o)
{ super("BasicMtlLibrary");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::BasicMtlLibrary",this);
theLib.recordNewInstance("BasicMtlLibrary",theCaller);
if (map.containsKey("BasicMtlASTView::Library")) 
	BMTLRef_BMTL_Library= (BMTL_Library)map.get("BasicMtlASTView::Library");
else BMTLRef_BMTL_Library= new BMTL_Library(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fLibrary().delete();
    theLib.removeInstance("BasicMtlLibrary",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_BasicMtlLibrary getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary()
{ return this.BMTLRef_BMTL_Library; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"BasicMtlLibrary"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol43)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol43)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol44)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol44)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol45)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol45)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol46)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol46)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol47)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol47)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol48) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol48)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol49) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol49)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol50)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol50)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol51,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol52,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol53) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol51,GenSymbol52,GenSymbol53)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol54,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol55,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol56) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol54,GenSymbol55,GenSymbol56)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol57,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol58,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol59) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol57,GenSymbol58,GenSymbol59)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol60) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol60)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol61,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol62) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol61,GenSymbol62)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol63) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol63)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_parameters()
{ return this.BMTL_parameters; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_parameters=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters(BasicMtlASTView.BMTL_ModelRefInterface BMTL_aModel) throws Throwable {
BooleanValue GenSymbol64 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_parameters());
if (GenSymbol64.getTheBoolean()) {
BMTL_parameters=new BMTLSet();
}
set_BMTL_parameters(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_parameters().BMTL_including(BMTL_aModel))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theParameters=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_classes=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_operations=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_ModelRefInterface BMTL_aParameter=null;
BasicMtlASTView.BMTL_UserClassInterface BMTL_aClass=null;
BasicMtlASTView.BMTL_OperationInterface BMTL_anOperation=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary theBasicMtlLibrary=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary(this.get_BMTL_name().getTheString());
BooleanValue GenSymbol65 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_parameters()).BMTL_not();
if (GenSymbol65.getTheBoolean()) {
BMTL_theParameters=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_parameters().BMTL_getNewIterator();
BMTL_theParameters.BMTL_start();
BooleanValue GenSymbol66;
do {GenSymbol66 = (BooleanValue)BMTL_theParameters.BMTL_isOn();
if (GenSymbol66.getTheBoolean()) {
BMTL_aParameter=(BasicMtlASTView.BMTL_ModelRefInterface)((BasicMtlASTView.BMTL_ModelRefInterface)CommonFunctions.toBMTLDataType(BMTL_theParameters.BMTL_item()));
theBasicMtlLibrary.appendParameters((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ModelRef)BMTL_aParameter.BMTL_toASTJava());
BMTL_theParameters.BMTL_next();
}

} while (GenSymbol66.getTheBoolean());
}
BooleanValue GenSymbol67 = (BooleanValue)theCaller.BMTL_isNull(((BasicMtlASTView.BMTL_LibraryInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations()).BMTL_not();
if (GenSymbol67.getTheBoolean()) {
BMTL_operations=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_LibraryInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations().BMTL_getNewIterator();
BMTL_operations.BMTL_start();
BooleanValue GenSymbol68;
do {GenSymbol68 = (BooleanValue)BMTL_operations.BMTL_isOn();
if (GenSymbol68.getTheBoolean()) {
BMTL_anOperation=(BasicMtlASTView.BMTL_OperationInterface)((BasicMtlASTView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_operations.BMTL_item()));
theBasicMtlLibrary.appendDefinedOperations((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation)BMTL_anOperation.BMTL_toASTJava());
BMTL_operations.BMTL_next();
}

} while (GenSymbol68.getTheBoolean());
}
BMTL_classes=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_LibraryInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses().BMTL_getNewIterator();
BMTL_classes.BMTL_start();
BooleanValue GenSymbol69;
do {GenSymbol69 = (BooleanValue)BMTL_classes.BMTL_isOn();
if (GenSymbol69.getTheBoolean()) {
BMTL_aClass=(BasicMtlASTView.BMTL_UserClassInterface)((BasicMtlASTView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(BMTL_classes.BMTL_item()));
theBasicMtlLibrary.appendDefinedClasses((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass)BMTL_aClass.BMTL_toASTJava());
BMTL_classes.BMTL_next();
}

} while (GenSymbol69.getTheBoolean());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol70;
do {GenSymbol70 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol70.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theBasicMtlLibrary.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol70.getTheBoolean());
return theBasicMtlLibrary;
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addClass(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_className) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_theInheritance=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_anInheritedParent=null;
BasicMtlASTView.BMTL_UserClassInterface BMTL_theCreatedClass=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_theLibName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_theName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_names=null;
BMTL_names=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_className.BMTL_getNewIterator();
BMTL_names.BMTL_start();
BMTL_theLibName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item()));
BMTL_names.BMTL_next();
BMTL_theName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item()));
BMTL_names.BMTL_next();
BMTL_theCreatedClass=(BasicMtlASTView.BMTL_UserClassInterface)((BasicMtlASTView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTView", "UserClass"})).instanciate()));
BMTL_theCreatedClass.set_BMTL_name(BMTL_theName);
BMTL_theInheritance=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate()));
BMTL_anInheritedParent=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_anInheritedParent=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_anInheritedParent.BMTL_append(BMTL_theLibName);
BMTL_anInheritedParent=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_anInheritedParent.BMTL_append(BMTL_theName);
BMTL_theInheritance=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)BMTL_theInheritance.BMTL_including(BMTL_anInheritedParent);
BooleanValue GenSymbol71;
do {GenSymbol71 = (BooleanValue)BMTL_names.BMTL_isOn();
if (GenSymbol71.getTheBoolean()) {
BMTL_anInheritedParent=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_anInheritedParent=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_anInheritedParent.BMTL_append(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item())));
BMTL_theInheritance=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)BMTL_theInheritance.BMTL_including(BMTL_anInheritedParent);
BMTL_names.BMTL_next();
}

} while (GenSymbol71.getTheBoolean());
((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theCreatedClass)).getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty(new BMTLString("Inheritance"),BMTL_theInheritance,new BMTLString("InheritanceTag"));
((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theCreatedClass)).getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-100000"),new BMTLString("StringTag"));
((BasicMtlASTView.BMTL_LibraryInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses(BMTL_theCreatedClass);
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isRefined(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_className,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_refinedClasses) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_theLibName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_theClassName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_refinedNames=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_names=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_aRefinedName=null;
BMTL_names=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_className.BMTL_getNewIterator();
BMTL_names.BMTL_start();
BMTL_theLibName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item()));
BMTL_names.BMTL_next();
BMTL_theClassName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item()));
BMTL_refinedNames=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_refinedClasses.BMTL_getNewIterator();
BMTL_refinedNames.BMTL_start();
BooleanValue GenSymbol72;
do {GenSymbol72 = (BooleanValue)BMTL_refinedNames.BMTL_isOn();
if (GenSymbol72.getTheBoolean()) {
BMTL_aRefinedName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(BMTL_refinedNames.BMTL_item()));
BMTL_names=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_aRefinedName.BMTL_getNewIterator();
BMTL_names.BMTL_start();
BooleanValue GenSymbol73 = (BooleanValue)BMTL_theLibName.BMTL__3d(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item())));
if (GenSymbol73.getTheBoolean()) {
BMTL_names.BMTL_next();
BooleanValue GenSymbol74 = (BooleanValue)BMTL_theClassName.BMTL__3d(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_names.BMTL_item())));
if (GenSymbol74.getTheBoolean()) {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface)CommonFunctions.toBMTLDataType(BMTLBoolean.TRUE);
}
}
BMTL_refinedNames.BMTL_next();
}

} while (GenSymbol72.getTheBoolean());
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface)CommonFunctions.toBMTLDataType(BMTLBoolean.FALSE);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addLibraries(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_inheritance,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_refinedClasses) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_librariesNames=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_classes=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_aLibName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_aClassName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_inheritedClasses=null;
BMTL_librariesNames=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_inheritance.BMTL_getNewIterator();
BMTL_librariesNames.BMTL_start();
BooleanValue GenSymbol75;
do {GenSymbol75 = (BooleanValue)BMTL_librariesNames.BMTL_isOn();
if (GenSymbol75.getTheBoolean()) {
BMTL_aLibName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(BMTL_librariesNames.BMTL_item()));
BMTL_inheritedClasses=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)theCaller.BMTL_allKnownClasses(BMTL_aLibName);
BMTL_classes=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_inheritedClasses.BMTL_getNewIterator();
BMTL_classes.BMTL_start();
BooleanValue GenSymbol76;
do {GenSymbol76 = (BooleanValue)BMTL_classes.BMTL_isOn();
if (GenSymbol76.getTheBoolean()) {
BMTL_aClassName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(BMTL_classes.BMTL_item()));
BooleanValue GenSymbol77 = (BooleanValue)theCaller.BMTL_isRefined(BMTL_aClassName,BMTL_refinedClasses).BMTL_not();
if (GenSymbol77.getTheBoolean()) {
theCaller.BMTL_addClass(BMTL_aClassName);
}
BMTL_classes.BMTL_next();
}

} while (GenSymbol76.getTheBoolean());
BMTL_librariesNames.BMTL_next();
}

} while (GenSymbol75.getTheBoolean());
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_findRefinedClasses() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_userClasses=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_theSet=null;
BasicMtlASTView.BMTL_UserClassInterface BMTL_aClass=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
BMTL_theSet=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate()));
BMTL_userClasses=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_LibraryInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses().BMTL_getNewIterator();
BMTL_userClasses.BMTL_start();
BooleanValue GenSymbol78;
do {GenSymbol78 = (BooleanValue)BMTL_userClasses.BMTL_isOn();
if (GenSymbol78.getTheBoolean()) {
BMTL_aClass=(BasicMtlASTView.BMTL_UserClassInterface)((BasicMtlASTView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(BMTL_userClasses.BMTL_item()));
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_aClass)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol79;
do {GenSymbol79 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol79.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
BooleanValue GenSymbol80 = (BooleanValue)BMTL_aProperty.get_BMTL_name().BMTL__3d(new BMTLString("Refinement"));
if (GenSymbol80.getTheBoolean()) {
BMTL_theSet=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)BMTL_theSet.BMTL_union(((BasicMtlASTView.BMTL_InheritancePropertyInterface)CommonFunctions.toBMTLDataType(BMTL_aProperty)).getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().get_BMTL_value());
}
BMTL_properties.BMTL_next();
}

} while (GenSymbol79.getTheBoolean());
BMTL_userClasses.BMTL_next();
}

} while (GenSymbol78.getTheBoolean());
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(BMTL_theSet);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_refinedClasses=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
BMTL_refinedClasses=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)null;
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol81;
do {GenSymbol81 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol81.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
BooleanValue GenSymbol82 = (BooleanValue)BMTL_aProperty.get_BMTL_name().BMTL__3d(new BMTLString("Inheritance"));
if (GenSymbol82.getTheBoolean()) {
BooleanValue GenSymbol83 = (BooleanValue)theCaller.BMTL_isNull(BMTL_refinedClasses);
if (GenSymbol83.getTheBoolean()) {
BMTL_refinedClasses=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)theCaller.BMTL_findRefinedClasses();
}
theCaller.BMTL_addLibraries(((BasicMtlASTView.BMTL_InheritancePropertyInterface)CommonFunctions.toBMTLDataType(BMTL_aProperty)).getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().get_BMTL_value(),BMTL_refinedClasses);
}
BMTL_properties.BMTL_next();
}

} while (GenSymbol81.getTheBoolean());
return BMTLVoid.TheInstance; }


}
