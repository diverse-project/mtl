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

public class BMTL_UserClass extends BMTLObject implements BMTL_UserClassInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_UserClass oclAsType(Object o)
{     return (BMTL_UserClass)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_UserClassInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_definedMethods;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_definedAttributes;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_UserClass(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("UserClass");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::UserClass",this);
theCaller=this;
theLib.recordNewInstance("UserClass",this);

if (inheritanceMap.containsKey("BasicMtlASTView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_UserClass(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_UserClassInterface o)
{ super("UserClass");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::UserClass",this);
theLib.recordNewInstance("UserClass",theCaller);
if (map.containsKey("BasicMtlASTView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)map.get("BasicMtlASTView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fASTNode().delete();
    theLib.removeInstance("UserClass",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_UserClass getRef_BMTL_BasicMtlASTView_5fUserClass()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"UserClass"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol530)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol530)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol531,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol532,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol533) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol531,GenSymbol532,GenSymbol533)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol534,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol535,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol536) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol534,GenSymbol535,GenSymbol536)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol537,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol538,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol539) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol537,GenSymbol538,GenSymbol539)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol540) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol540)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol541,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol542) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol541,GenSymbol542)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol543) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol543)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedMethods()
{ return this.BMTL_definedMethods; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedMethods (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_definedMethods=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedAttributes()
{ return this.BMTL_definedAttributes; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedAttributes (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_definedAttributes=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedAttributes(BasicMtlASTView.BMTL_AttributeInterface BMTL_attrib) throws Throwable {
BooleanValue GenSymbol544 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_definedAttributes());
if (GenSymbol544.getTheBoolean()) {
BMTL_definedAttributes=new BMTLSet();
}
set_BMTL_definedAttributes(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_definedAttributes().BMTL_including(BMTL_attrib))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedMethods(BasicMtlASTView.BMTL_OperationInterface BMTL_op) throws Throwable {
BooleanValue GenSymbol545 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_definedMethods());
if (GenSymbol545.getTheBoolean()) {
BMTL_definedMethods=new BMTLSet();
}
set_BMTL_definedMethods(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_definedMethods().BMTL_including(BMTL_op))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_joinAttributeOperation(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isGetter,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_attributeName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_operationName) throws Throwable {
BasicMtlASTView.BMTL_AttributeInterface BMTL_theAttribute=null;
BasicMtlASTView.BMTL_OperationInterface BMTL_theOperation=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_features=null;
BMTL_theAttribute=(BasicMtlASTView.BMTL_AttributeInterface)null;
BMTL_theOperation=(BasicMtlASTView.BMTL_OperationInterface)null;
BMTL_features=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_definedAttributes().BMTL_getNewIterator();
BMTL_features.BMTL_start();
BooleanValue GenSymbol546;
do {GenSymbol546 = (BooleanValue)BMTL_features.BMTL_isOn();
if (GenSymbol546.getTheBoolean()) {
BooleanValue GenSymbol547 = (BooleanValue)((BasicMtlASTView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(BMTL_features.BMTL_item())).getRef_BMTL_BasicMtlASTView_5fAttribute().get_BMTL_name().BMTL__3d(BMTL_attributeName);
if (GenSymbol547.getTheBoolean()) {
BMTL_theAttribute=(BasicMtlASTView.BMTL_AttributeInterface)((BasicMtlASTView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(BMTL_features.BMTL_item()));
}
BMTL_features.BMTL_next();
}

} while (GenSymbol546.getTheBoolean());
BMTL_features=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_definedMethods().BMTL_getNewIterator();
BMTL_features.BMTL_start();
BooleanValue GenSymbol548;
do {GenSymbol548 = (BooleanValue)BMTL_features.BMTL_isOn();
if (GenSymbol548.getTheBoolean()) {
BooleanValue GenSymbol549 = (BooleanValue)((BasicMtlASTView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_features.BMTL_item())).getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_name().BMTL__3d(BMTL_operationName);
if (GenSymbol549.getTheBoolean()) {
BMTL_theOperation=(BasicMtlASTView.BMTL_OperationInterface)((BasicMtlASTView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_features.BMTL_item()));
}
BMTL_features.BMTL_next();
}

} while (GenSymbol548.getTheBoolean());
BooleanValue GenSymbol550 = (BooleanValue)theCaller.BMTL_isNull(BMTL_theAttribute).BMTL_or(theCaller.BMTL_isNull(BMTL_theOperation));
if (GenSymbol550.getTheBoolean()) {
new BMTLString("Getter/Setter attribute or operation not found !").BMTL_toErr();
BMTL_isGetter.BMTL_toErr();
BMTL_attributeName.BMTL_toErr();
BMTL_operationName.BMTL_toErr();
} else {
BooleanValue GenSymbol551 = (BooleanValue)BMTL_isGetter;
if (GenSymbol551.getTheBoolean()) {
BMTL_theAttribute.set_BMTL_possibleGetter(BMTL_theOperation);
BMTL_theOperation.set_BMTL_isGetterFor(BMTL_theAttribute);
} else {
BMTL_theAttribute.set_BMTL_possibleSetter(BMTL_theOperation);
BMTL_theOperation.set_BMTL_isSetterFor(BMTL_theAttribute);
}
}
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_attributeName=null;
BasicMtlASTView.BMTL_AttributeInterface BMTL_anAttribute=null;
BasicMtlASTView.BMTL_OperationInterface BMTL_aMethod=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_attributes=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_methods=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass theClass=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass(BMTL_name.getTheString());
BooleanValue GenSymbol552 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_definedAttributes()).BMTL_not();
if (GenSymbol552.getTheBoolean()) {
BMTL_attributes=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_definedAttributes().BMTL_getNewIterator();
BMTL_attributes.BMTL_start();
BooleanValue GenSymbol553;
do {GenSymbol553 = (BooleanValue)BMTL_attributes.BMTL_isOn();
if (GenSymbol553.getTheBoolean()) {
BMTL_anAttribute=(BasicMtlASTView.BMTL_AttributeInterface)((BasicMtlASTView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(BMTL_attributes.BMTL_item()));
theClass.appendDefinedAttributes((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute)BMTL_anAttribute.BMTL_toASTJava());
BMTL_attributes.BMTL_next();
}

} while (GenSymbol553.getTheBoolean());
}
BooleanValue GenSymbol554 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_definedMethods()).BMTL_not();
if (GenSymbol554.getTheBoolean()) {
BMTL_methods=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_definedMethods().BMTL_getNewIterator();
BMTL_methods.BMTL_start();
BooleanValue GenSymbol555;
do {GenSymbol555 = (BooleanValue)BMTL_methods.BMTL_isOn();
if (GenSymbol555.getTheBoolean()) {
BMTL_aMethod=(BasicMtlASTView.BMTL_OperationInterface)((BasicMtlASTView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_methods.BMTL_item()));
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation theASTMethod=(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation)BMTL_aMethod.BMTL_toASTJava();
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute theASTAttribute=null;
theClass.appendDefinedMethods(theASTMethod);
BooleanValue GenSymbol556 = (BooleanValue)theCaller.BMTL_isNull(BMTL_aMethod.get_BMTL_isGetterFor()).BMTL_not();
if (GenSymbol556.getTheBoolean()) {
BMTL_attributeName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)BMTL_aMethod.get_BMTL_isGetterFor().get_BMTL_name();
theASTAttribute = theClass.findAttribute(BMTL_attributeName.getTheString());
theASTAttribute.setGetter(theASTMethod);
theASTMethod.setIsGetterFor(theASTAttribute);
}
BooleanValue GenSymbol557 = (BooleanValue)theCaller.BMTL_isNull(BMTL_aMethod.get_BMTL_isSetterFor()).BMTL_not();
if (GenSymbol557.getTheBoolean()) {
BMTL_attributeName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)BMTL_aMethod.get_BMTL_isSetterFor().get_BMTL_name();
theASTAttribute = theClass.findAttribute(BMTL_attributeName.getTheString());
theASTAttribute.setSetter(theASTMethod);
theASTMethod.setIsSetterFor(theASTAttribute);
}
BMTL_methods.BMTL_next();
}

} while (GenSymbol555.getTheBoolean());
}
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol558;
do {GenSymbol558 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol558.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theClass.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol558.getTheBoolean());
return theClass;
}


}
