package BasicMtlASTWithAssociationView;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public class BMTL_Association extends BMTLObject implements BMTL_AssociationInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Association oclAsType(Object o)
{     return (BMTL_Association)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_AssociationInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTWithAssociationView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_endPoints;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Association(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Association");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Association",this);
theCaller=this;
theLib.recordNewInstance("Association",this);

if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Association(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_AssociationInterface o)
{ super("Association");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Association",this);
theLib.recordNewInstance("Association",theCaller);
if (map.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)map.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().delete();
    theLib.removeInstance("Association",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Association getRef_BMTL_BasicMtlASTWithAssociationView_5fAssociation()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Association"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol1)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol1)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol2,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol3,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol4) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol2,GenSymbol3,GenSymbol4)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol5,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol6,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol7) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol5,GenSymbol6,GenSymbol7)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol8,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol9,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol10) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol8,GenSymbol9,GenSymbol10)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol11) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol11)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol12,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol13) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol12,GenSymbol13)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol14) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol14)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_endPoints()
{ return this.BMTL_endPoints; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_endPoints (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_endPoints=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendEndPoints(BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_point) throws Throwable {
BooleanValue GenSymbol15 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_endPoints());
if (GenSymbol15.getTheBoolean()) {
BMTL_endPoints=new BMTLSet();
}
set_BMTL_endPoints(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_endPoints().BMTL_including(BMTL_point))));
return BMTLVoid.TheInstance; }


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_copyOperation(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_theOp) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)((BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Operation"})).instanciate()));
BMTL_copy.set_BMTL_name(BMTL_theOp.get_BMTL_name());
BooleanValue GenSymbol16 = (BooleanValue)theCaller.BMTL_isNull(BMTL_theOp.get_BMTL_Parameters()).BMTL_not();
if (GenSymbol16.getTheBoolean()) {
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_theOp.get_BMTL_Parameters().BMTL_getNewIterator();
BMTL_copy.set_BMTL_Parameters(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_elements.BMTL_start();
BooleanValue GenSymbol17;
do {GenSymbol17 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol17.getTheBoolean()) {
BMTL_copy.set_BMTL_Parameters(BMTL_copy.get_BMTL_Parameters().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol17.getTheBoolean());
}
BooleanValue GenSymbol18 = (BooleanValue)theCaller.BMTL_isNull(BMTL_theOp.get_BMTL_declaredVariables()).BMTL_not();
if (GenSymbol18.getTheBoolean()) {
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_theOp.get_BMTL_declaredVariables().BMTL_getNewIterator();
BMTL_copy.set_BMTL_declaredVariables(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_elements.BMTL_start();
BooleanValue GenSymbol19;
do {GenSymbol19 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol19.getTheBoolean()) {
BMTL_copy.set_BMTL_declaredVariables(BMTL_copy.get_BMTL_declaredVariables().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol19.getTheBoolean());
}
BooleanValue GenSymbol20 = (BooleanValue)theCaller.BMTL_isNull(BMTL_theOp.get_BMTL_instructions()).BMTL_not();
if (GenSymbol20.getTheBoolean()) {
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_theOp.get_BMTL_instructions().BMTL_getNewIterator();
BMTL_copy.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_elements.BMTL_start();
BooleanValue GenSymbol21;
do {GenSymbol21 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol21.getTheBoolean()) {
BMTL_copy.set_BMTL_instructions(BMTL_copy.get_BMTL_instructions().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol21.getTheBoolean());
}
BMTL_copy.set_BMTL_throwsExceptionValue(BMTL_theOp.get_BMTL_throwsExceptionValue());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theOp)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol22;
do {GenSymbol22 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol22.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol22.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_copyOperationCall(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_theOpCall) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "OperationCall"})).instanciate()));
BMTL_copy.set_BMTL_name(BMTL_theOpCall.get_BMTL_name());
BMTL_copy.set_BMTL_caller(BMTL_theOpCall.get_BMTL_caller());
BooleanValue GenSymbol23 = (BooleanValue)theCaller.BMTL_isNull(BMTL_theOpCall.get_BMTL_arguments()).BMTL_not();
if (GenSymbol23.getTheBoolean()) {
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_theOpCall.get_BMTL_arguments().BMTL_getNewIterator();
BMTL_copy.set_BMTL_arguments(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_elements.BMTL_start();
BooleanValue GenSymbol24;
do {GenSymbol24 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol24.getTheBoolean()) {
BMTL_copy.set_BMTL_arguments(BMTL_copy.get_BMTL_arguments().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_ExpressionInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol24.getTheBoolean());
}
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theOpCall)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol25;
do {GenSymbol25 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol25.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol25.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_copyOclAsType(BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_theOclAsType) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)((BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "OclAsType"})).instanciate()));
BMTL_copy.set_BMTL_expression(BMTL_theOclAsType.get_BMTL_expression());
BMTL_copy.set_BMTL_type(BMTL_theOclAsType.get_BMTL_type());
BMTL_copy.set_BMTL_typeVar(BMTL_theOclAsType.get_BMTL_typeVar());
BMTL_copy.set_BMTL_methodVar(BMTL_theOclAsType.get_BMTL_methodVar());
BMTL_copy.set_BMTL_parameterVar(BMTL_theOclAsType.get_BMTL_parameterVar());
BMTL_copy.set_BMTL_isAConstant(BMTL_theOclAsType.get_BMTL_isAConstant());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theOclAsType)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol26;
do {GenSymbol26 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol26.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol26.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copyIf(BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_theIf) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "If"})).instanciate()));
BMTL_copy.set_BMTL_condition(BMTL_theIf.get_BMTL_condition());
BMTL_copy.set_BMTL_thenBody(BMTL_theIf.get_BMTL_thenBody());
BMTL_copy.set_BMTL_elseBody(BMTL_theIf.get_BMTL_elseBody());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theIf)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol27;
do {GenSymbol27 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol27.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol27.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_WhileInterface BMTL_copyWhile(BasicMtlASTWithAssociationView.BMTL_WhileInterface BMTL_theWhile) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_WhileInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_WhileInterface)((BasicMtlASTWithAssociationView.BMTL_WhileInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "While"})).instanciate()));
BMTL_copy.set_BMTL_condition(BMTL_theWhile.get_BMTL_condition());
BMTL_copy.set_BMTL_body(BMTL_theWhile.get_BMTL_body());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theWhile)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol28;
do {GenSymbol28 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol28.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol28.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_WhileInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_copyReturn(BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_theReturn) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Return"})).instanciate()));
BMTL_copy.set_BMTL_returnedExpression(BMTL_theReturn.get_BMTL_returnedExpression());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theReturn)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol29;
do {GenSymbol29 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol29.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol29.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_copyVarDeclaration(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_theVarDeclaration) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "VarDeclaration"})).instanciate()));
BMTL_copy.set_BMTL_name(BMTL_theVarDeclaration.get_BMTL_name());
BMTL_copy.set_BMTL_isFormalParameter(BMTL_theVarDeclaration.get_BMTL_isFormalParameter());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theVarDeclaration)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol30;
do {GenSymbol30 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol30.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol30.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_copyVarSetting(BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_theVarSetting) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)((BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "VarSetting"})).instanciate()));
BMTL_copy.set_BMTL_varName(BMTL_theVarSetting.get_BMTL_varName());
BMTL_copy.set_BMTL_value(BMTL_theVarSetting.get_BMTL_value());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theVarSetting)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol31;
do {GenSymbol31 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol31.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol31.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_NewObjectInterface BMTL_copyNewObject(BasicMtlASTWithAssociationView.BMTL_NewObjectInterface BMTL_theNewObject) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_NewObjectInterface BMTL_copy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_elements=null;
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_NewObjectInterface)((BasicMtlASTWithAssociationView.BMTL_NewObjectInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "NewObject"})).instanciate()));
BMTL_copy.set_BMTL_arguments(BMTL_theNewObject.get_BMTL_arguments());
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_copy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_elements=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theNewObject)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_elements.BMTL_start();
BooleanValue GenSymbol32;
do {GenSymbol32 = (BooleanValue)BMTL_elements.BMTL_isOn();
if (GenSymbol32.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(BMTL_node.get_BMTL_decoration().BMTL_including(((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_elements.BMTL_item()))));
BMTL_elements.BMTL_next();
}

} while (GenSymbol32.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_NewObjectInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_templateGetter(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_roleGetter,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isOrdered) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnCopy=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_operationCallCopy1=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_operationCallCopy2=null;
BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface BMTL_stringLiteralCopy=null;
BasicMtlASTWithAssociationView.BMTL_ExpressionInterface BMTL_secondArg=null;
BMTL_returnCopy=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)theCaller.BMTL_copyReturn(((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_roleGetter.get_BMTL_instructions().BMTL_at(new BMTLInteger(1)))));
BMTL_roleGetter.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_roleGetter.set_BMTL_instructions(BMTL_roleGetter.get_BMTL_instructions().BMTL_including(BMTL_returnCopy));
BMTL_operationCallCopy1=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_returnCopy.get_BMTL_returnedExpression())));
BMTL_secondArg=(BasicMtlASTWithAssociationView.BMTL_ExpressionInterface)BMTL_operationCallCopy1.get_BMTL_arguments().BMTL_at(new BMTLInteger(2));
BMTL_returnCopy.set_BMTL_returnedExpression(BMTL_operationCallCopy1);
BMTL_operationCallCopy2=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_operationCallCopy1.get_BMTL_caller())));
BMTL_operationCallCopy1.set_BMTL_caller(BMTL_operationCallCopy2);
BMTL_stringLiteralCopy=(BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"})).instanciate()));
BMTL_operationCallCopy1.set_BMTL_arguments(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_operationCallCopy1.set_BMTL_arguments(BMTL_operationCallCopy1.get_BMTL_arguments().BMTL_including(BMTL_stringLiteralCopy));
BMTL_operationCallCopy1.set_BMTL_arguments(BMTL_operationCallCopy1.get_BMTL_arguments().BMTL_including(BMTL_secondArg));
BMTL_operationCallCopy2.set_BMTL_name(BMTL_associationName);
BMTL_stringLiteralCopy.set_BMTL_value(BMTL_roleName);
BooleanValue GenSymbol33 = (BooleanValue)BMTL_isOrdered;
if (GenSymbol33.getTheBoolean()) {
BMTL_roleGetter.set_BMTL_name(new BMTLString("getOrdered").BMTL_concat(BMTL_associationName).BMTL_concat(BMTL_roleName));
} else {
BMTL_roleGetter.set_BMTL_name(new BMTLString("get").BMTL_concat(BMTL_associationName).BMTL_concat(BMTL_roleName));
}
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_templateSetter(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_roleSetter,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isOrdered) throws Throwable {
BooleanValue GenSymbol34 = (BooleanValue)BMTL_isOrdered;
if (GenSymbol34.getTheBoolean()) {
BMTL_roleSetter.set_BMTL_name(new BMTLString("setOrdered").BMTL_concat(BMTL_associationName).BMTL_concat(BMTL_roleName));
} else {
BMTL_roleSetter.set_BMTL_name(new BMTLString("set").BMTL_concat(BMTL_associationName).BMTL_concat(BMTL_roleName));
}
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_templateSetLink(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_setLink,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_operationCallCopy1=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_associationType=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_paramCopy=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
BMTL_setLink.set_BMTL_name(BMTL_associationName.BMTL_concat(new BMTLString("setLink")));
BMTL_paramCopy=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)theCaller.BMTL_copyVarDeclaration(((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_setLink.get_BMTL_Parameters().BMTL_at(new BMTLInteger(1)))));
BMTL_setLink.set_BMTL_Parameters(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_setLink.set_BMTL_Parameters(BMTL_setLink.get_BMTL_Parameters().BMTL_including(BMTL_paramCopy));
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_paramCopy));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_associationType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_associationType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_associationType.BMTL_append(BMTL_associationName);
BMTL_node.BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_associationType,new BMTLString("TypeTag"));
BMTL_node.BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_operationCallCopy1=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_setLink.get_BMTL_instructions().BMTL_at(new BMTLInteger(1)))));
BMTL_setLink.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_setLink.set_BMTL_instructions(BMTL_setLink.get_BMTL_instructions().BMTL_including(BMTL_operationCallCopy1));
BMTL_operationCallCopy1.set_BMTL_name(BMTL_associationName);
return BMTLVoid.TheInstance; }


public BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_templateMultiplicityInstr(BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_instr,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface BMTL_multiplicityBound) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copy=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_newArguments=null;
BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface BMTL_newStringLiteral=null;
BasicMtlASTWithAssociationView.BMTL_IntLiteralInterface BMTL_newIntLiteral=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_equalTest=null;
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnInstr=null;
BMTL_equalTest=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_condition()));
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(BMTL_instr);
BMTL_copy.set_BMTL_condition(theCaller.BMTL_copyOperationCall(BMTL_equalTest));
BMTL_newStringLiteral=(BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"})).instanciate()));
BMTL_newStringLiteral.set_BMTL_value(BMTL_roleName);
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_newArguments.BMTL_including(BMTL_newStringLiteral);
((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_copy.get_BMTL_condition())).getRef_BMTL_BasicMtlASTWithAssociationView_5fOperationCall().set_BMTL_arguments(BMTL_newArguments);
BMTL_returnInstr=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1))));
BMTL_copy.set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_copy.set_BMTL_thenBody(BMTL_copy.get_BMTL_thenBody().BMTL_including(theCaller.BMTL_copyReturn(BMTL_returnInstr)));
BMTL_newIntLiteral=(BasicMtlASTWithAssociationView.BMTL_IntLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_IntLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "IntLiteral"})).instanciate()));
BMTL_newIntLiteral.set_BMTL_value(BMTL_multiplicityBound);
((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_copy.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1)))).getRef_BMTL_BasicMtlASTWithAssociationView_5fReturn().set_BMTL_returnedExpression(BMTL_newIntLiteral);
return (BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_templateIsOrderedInstr(BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_instr,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copy=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_newArguments=null;
BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface BMTL_newStringLiteral=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_equalTest=null;
BMTL_equalTest=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_condition()));
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(BMTL_instr);
BMTL_copy.set_BMTL_condition(theCaller.BMTL_copyOperationCall(BMTL_equalTest));
BMTL_newStringLiteral=(BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"})).instanciate()));
BMTL_newStringLiteral.set_BMTL_value(BMTL_roleName);
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_newArguments.BMTL_including(BMTL_newStringLiteral);
((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_copy.get_BMTL_condition())).getRef_BMTL_BasicMtlASTWithAssociationView_5fOperationCall().set_BMTL_arguments(BMTL_newArguments);
return (BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_templateGetInstr(BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_instr,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copy=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_newArguments=null;
BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface BMTL_newStringLiteral=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_opCallCopy=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_equalTest=null;
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnInstr=null;
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnCopy=null;
BMTL_equalTest=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_condition()));
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(BMTL_instr);
BMTL_copy.set_BMTL_condition(theCaller.BMTL_copyOperationCall(BMTL_equalTest));
BMTL_newStringLiteral=(BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"})).instanciate()));
BMTL_newStringLiteral.set_BMTL_value(BMTL_roleName);
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_newArguments.BMTL_including(BMTL_newStringLiteral);
((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_copy.get_BMTL_condition())).getRef_BMTL_BasicMtlASTWithAssociationView_5fOperationCall().set_BMTL_arguments(BMTL_newArguments);
BMTL_returnInstr=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1))));
BMTL_opCallCopy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_returnInstr.get_BMTL_returnedExpression())));
BMTL_opCallCopy.set_BMTL_name(BMTL_roleName);
BMTL_returnCopy=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)theCaller.BMTL_copyReturn(BMTL_returnInstr);
BMTL_returnCopy.set_BMTL_returnedExpression(BMTL_opCallCopy);
BMTL_copy.set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_copy.set_BMTL_thenBody(BMTL_copy.get_BMTL_thenBody().BMTL_including(BMTL_returnCopy));
return (BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_templateSetInstr(BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_instr,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_roleName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_copy=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_newArguments=null;
BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface BMTL_newStringLiteral=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_opCallCopy=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_equalTest=null;
BMTL_equalTest=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_condition()));
BMTL_copy=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(BMTL_instr);
BMTL_copy.set_BMTL_condition(theCaller.BMTL_copyOperationCall(BMTL_equalTest));
BMTL_newStringLiteral=(BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)((BasicMtlASTWithAssociationView.BMTL_StringLiteralInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"})).instanciate()));
BMTL_newStringLiteral.set_BMTL_value(BMTL_roleName);
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_newArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_newArguments.BMTL_including(BMTL_newStringLiteral);
((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_copy.get_BMTL_condition())).getRef_BMTL_BasicMtlASTWithAssociationView_5fOperationCall().set_BMTL_arguments(BMTL_newArguments);
BMTL_opCallCopy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_instr.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1)))));
BMTL_opCallCopy.set_BMTL_name(BMTL_roleName);
BMTL_copy.set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_copy.set_BMTL_thenBody(BMTL_copy.get_BMTL_thenBody().BMTL_including(BMTL_opCallCopy));
return (BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_copy);
}


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_templateGetRole(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_op,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_firstVS=null;
BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_secondVS=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_firstIf=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_secondIf=null;
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnCopy=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_opCallCopy=null;
BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_oclAsTypeCopy=null;
BMTL_firstVS=(BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(1));
BMTL_secondVS=(BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(2));
BMTL_firstIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(3));
BMTL_secondIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(4));
BMTL_returnCopy=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)theCaller.BMTL_copyReturn(((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(5)))));
BMTL_opCallCopy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_returnCopy.get_BMTL_returnedExpression())));
BMTL_oclAsTypeCopy=(BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)theCaller.BMTL_copyOclAsType(((BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)CommonFunctions.toBMTLDataType(BMTL_opCallCopy.get_BMTL_caller())));
BMTL_oclAsTypeCopy.set_BMTL_type(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_oclAsTypeCopy.set_BMTL_type(BMTL_oclAsTypeCopy.get_BMTL_type().BMTL_including(BMTL_associationName));
BMTL_opCallCopy.set_BMTL_caller(BMTL_oclAsTypeCopy);
BMTL_returnCopy.set_BMTL_returnedExpression(BMTL_opCallCopy);
BMTL_op.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_firstVS));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_secondVS));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_firstIf));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_secondIf));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_returnCopy));
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_op);
}


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_templateBuildLink(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_op,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl1=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl2=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl3=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl4=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl5=null;
BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface BMTL_varDecl6=null;
BasicMtlASTWithAssociationView.BMTL_VarSettingInterface BMTL_varSettingCopy=null;
BasicMtlASTWithAssociationView.BMTL_NewObjectInterface BMTL_newAssociation=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr1=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr2=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr3=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr4=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr5=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr6=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr7=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr8=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr9=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_instr10=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_associationType=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
BMTL_varDecl1=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)theCaller.BMTL_copyVarDeclaration(((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(1)))));
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_varDecl1));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_associationType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_associationType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_associationType.BMTL_append(BMTL_associationName);
BMTL_node.BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_associationType,new BMTLString("TypeTag"));
BMTL_node.BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_varDecl2=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(2))));
BMTL_varDecl3=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(3))));
BMTL_varDecl4=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(4))));
BMTL_varDecl5=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(5))));
BMTL_varDecl6=(BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)((BasicMtlASTWithAssociationView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_declaredVariables().BMTL_at(new BMTLInteger(6))));
BMTL_op.set_BMTL_declaredVariables(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl1));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl2));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl3));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl4));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl5));
BMTL_op.set_BMTL_declaredVariables(BMTL_op.get_BMTL_declaredVariables().BMTL_including(BMTL_varDecl6));
BMTL_varSettingCopy=(BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)theCaller.BMTL_copyVarSetting(((BasicMtlASTWithAssociationView.BMTL_VarSettingInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(1)))));
BMTL_newAssociation=(BasicMtlASTWithAssociationView.BMTL_NewObjectInterface)theCaller.BMTL_copyNewObject(((BasicMtlASTWithAssociationView.BMTL_NewObjectInterface)CommonFunctions.toBMTLDataType(BMTL_varSettingCopy.get_BMTL_value())));
BMTL_instr1=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(2));
BMTL_instr2=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(3));
BMTL_instr3=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(4));
BMTL_instr4=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(5));
BMTL_instr5=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(6));
BMTL_instr6=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(7));
BMTL_instr7=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(8));
BMTL_instr8=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(9));
BMTL_instr9=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(10));
BMTL_instr10=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(11));
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_newAssociation));
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
BMTL_node.BMTL_createNewBMTLTypeProperty(new BMTLString("TypeToCreate"),BMTL_associationType,new BMTLString("TypeTag"));
BMTL_node.BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_varSettingCopy.set_BMTL_value(BMTL_newAssociation);
BMTL_op.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_varSettingCopy));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr1));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr2));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr3));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr4));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr5));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr6));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr7));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr8));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr9));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_instr10));
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_op);
}


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_templateAssociateOperation(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_op,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_firstIf=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_secondIf=null;
BasicMtlASTWithAssociationView.BMTL_WhileInterface BMTL_firstWhile=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_firstIfInstr1=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_firstIfInstr2=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr1=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr2=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr3=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr4=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr5=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr7=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_opCallCopy=null;
BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_oclAsTypeCopy=null;
BMTL_firstIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(1)))));
BMTL_firstIfInstr1=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstIf.get_BMTL_elseBody().BMTL_at(new BMTLInteger(1));
BMTL_firstIfInstr2=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstIf.get_BMTL_elseBody().BMTL_at(new BMTLInteger(2));
BMTL_firstWhile=(BasicMtlASTWithAssociationView.BMTL_WhileInterface)theCaller.BMTL_copyWhile(((BasicMtlASTWithAssociationView.BMTL_WhileInterface)CommonFunctions.toBMTLDataType(BMTL_firstIf.get_BMTL_elseBody().BMTL_at(new BMTLInteger(3)))));
BMTL_whileInstr1=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(1));
BMTL_whileInstr2=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(2));
BMTL_whileInstr3=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(3));
BMTL_whileInstr4=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(4));
BMTL_whileInstr5=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(5));
BMTL_secondIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(6)))));
BMTL_whileInstr7=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(7));
BMTL_opCallCopy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_secondIf.get_BMTL_elseBody().BMTL_at(new BMTLInteger(1)))));
BMTL_oclAsTypeCopy=(BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)theCaller.BMTL_copyOclAsType(((BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)CommonFunctions.toBMTLDataType(BMTL_opCallCopy.get_BMTL_caller())));
BMTL_oclAsTypeCopy.set_BMTL_type(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_oclAsTypeCopy.set_BMTL_type(BMTL_oclAsTypeCopy.get_BMTL_type().BMTL_including(BMTL_associationName));
BMTL_opCallCopy.set_BMTL_caller(BMTL_oclAsTypeCopy);
BMTL_secondIf.set_BMTL_elseBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_secondIf.set_BMTL_elseBody(BMTL_secondIf.get_BMTL_elseBody().BMTL_including(BMTL_opCallCopy));
BMTL_firstWhile.set_BMTL_body(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr1));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr2));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr3));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr4));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr5));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_secondIf));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr7));
BMTL_firstIf.set_BMTL_elseBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_firstIf.set_BMTL_elseBody(BMTL_firstIf.get_BMTL_elseBody().BMTL_including(BMTL_firstIfInstr1));
BMTL_firstIf.set_BMTL_elseBody(BMTL_firstIf.get_BMTL_elseBody().BMTL_including(BMTL_firstIfInstr2));
BMTL_firstIf.set_BMTL_elseBody(BMTL_firstIf.get_BMTL_elseBody().BMTL_including(BMTL_firstWhile));
BMTL_op.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_firstIf));
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_op);
}


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_templateDissociateOperation(BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_op,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_firstIf=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_secondIf=null;
BasicMtlASTWithAssociationView.BMTL_WhileInterface BMTL_firstWhile=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_firstIfInstr1=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_firstIfInstr2=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr1=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr2=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr3=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr4=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr5=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_whileInstr7=null;
BasicMtlASTWithAssociationView.BMTL_OperationCallInterface BMTL_opCallCopy=null;
BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface BMTL_oclAsTypeCopy=null;
BMTL_firstIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_op.get_BMTL_instructions().BMTL_at(new BMTLInteger(1)))));
BMTL_firstIfInstr1=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstIf.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1));
BMTL_firstIfInstr2=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstIf.get_BMTL_thenBody().BMTL_at(new BMTLInteger(2));
BMTL_firstWhile=(BasicMtlASTWithAssociationView.BMTL_WhileInterface)theCaller.BMTL_copyWhile(((BasicMtlASTWithAssociationView.BMTL_WhileInterface)CommonFunctions.toBMTLDataType(BMTL_firstIf.get_BMTL_thenBody().BMTL_at(new BMTLInteger(3)))));
BMTL_whileInstr1=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(1));
BMTL_whileInstr2=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(2));
BMTL_whileInstr3=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(3));
BMTL_whileInstr4=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(4));
BMTL_whileInstr5=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(5));
BMTL_secondIf=(BasicMtlASTWithAssociationView.BMTL_IfInterface)theCaller.BMTL_copyIf(((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(6)))));
BMTL_whileInstr7=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_firstWhile.get_BMTL_body().BMTL_at(new BMTLInteger(7));
BMTL_opCallCopy=(BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)theCaller.BMTL_copyOperationCall(((BasicMtlASTWithAssociationView.BMTL_OperationCallInterface)CommonFunctions.toBMTLDataType(BMTL_secondIf.get_BMTL_thenBody().BMTL_at(new BMTLInteger(1)))));
BMTL_oclAsTypeCopy=(BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)theCaller.BMTL_copyOclAsType(((BasicMtlASTWithAssociationView.BMTL_OclAsTypeInterface)CommonFunctions.toBMTLDataType(BMTL_opCallCopy.get_BMTL_caller())));
BMTL_oclAsTypeCopy.set_BMTL_type(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_oclAsTypeCopy.set_BMTL_type(BMTL_oclAsTypeCopy.get_BMTL_type().BMTL_including(BMTL_associationName));
BMTL_opCallCopy.set_BMTL_caller(BMTL_oclAsTypeCopy);
BMTL_secondIf.set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_secondIf.set_BMTL_thenBody(BMTL_secondIf.get_BMTL_thenBody().BMTL_including(BMTL_opCallCopy));
BMTL_firstWhile.set_BMTL_body(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr1));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr2));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr3));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr4));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr5));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_secondIf));
BMTL_firstWhile.set_BMTL_body(BMTL_firstWhile.get_BMTL_body().BMTL_including(BMTL_whileInstr7));
BMTL_firstIf.set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_firstIf.set_BMTL_thenBody(BMTL_firstIf.get_BMTL_thenBody().BMTL_including(BMTL_firstIfInstr1));
BMTL_firstIf.set_BMTL_thenBody(BMTL_firstIf.get_BMTL_thenBody().BMTL_including(BMTL_firstIfInstr2));
BMTL_firstIf.set_BMTL_thenBody(BMTL_firstIf.get_BMTL_thenBody().BMTL_including(BMTL_firstWhile));
BMTL_op.set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate())));
BMTL_op.set_BMTL_instructions(BMTL_op.get_BMTL_instructions().BMTL_including(BMTL_firstIf));
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_op);
}


public BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_getNamedOperation(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_opName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_operationTemplates) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theOperations=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_anOperation=null;
BMTL_theOperations=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_operationTemplates.BMTL_getNewIterator();
BMTL_theOperations.BMTL_start();
BooleanValue GenSymbol35;
do {GenSymbol35 = (BooleanValue)BMTL_theOperations.BMTL_isOn();
if (GenSymbol35.getTheBoolean()) {
BMTL_anOperation=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)((BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(BMTL_theOperations.BMTL_item()));
BooleanValue GenSymbol36 = (BooleanValue)BMTL_anOperation.get_BMTL_name().BMTL__3d(BMTL_opName);
if (GenSymbol36.getTheBoolean()) {
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(theCaller.BMTL_copyOperation(BMTL_anOperation));
}
BMTL_theOperations.BMTL_next();
}

} while (GenSymbol35.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_OperationInterface)CommonFunctions.toBMTLDataType(null);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_makeTheAssociationName() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theEndPoints=null;
BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anEndPoint=null;
BMTL_associationName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)new BMTLString("association");
BooleanValue GenSymbol37 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_name()).BMTL_not();
if (GenSymbol37.getTheBoolean()) {
BMTL_associationName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)BMTL_associationName.BMTL_concat(get_BMTL_name());
}
BMTL_theEndPoints=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_endPoints().BMTL_getNewIterator();
BMTL_theEndPoints.BMTL_start();
BooleanValue GenSymbol38;
do {GenSymbol38 = (BooleanValue)BMTL_theEndPoints.BMTL_isOn();
if (GenSymbol38.getTheBoolean()) {
BMTL_anEndPoint=(BasicMtlASTWithAssociationView.BMTL_EndPointInterface)((BasicMtlASTWithAssociationView.BMTL_EndPointInterface)CommonFunctions.toBMTLDataType(BMTL_theEndPoints.BMTL_item()));
BooleanValue GenSymbol39 = (BooleanValue)theCaller.BMTL_isNull(BMTL_anEndPoint.get_BMTL_roleName());
if (GenSymbol39.getTheBoolean()) {
new BMTLString("Each EndPoint of an association should have a role name").BMTL_toErr();
BMTL_associationName.BMTL_toErr();
} else {
BMTL_associationName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)BMTL_associationName.BMTL_concat(BMTL_anEndPoint.get_BMTL_roleName());
}
BMTL_theEndPoints.BMTL_next();
}

} while (GenSymbol38.getTheBoolean());
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_associationName);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_makeRoleGetterSetter(BasicMtlASTWithAssociationView.BMTL_UserClassInterface BMTL_aClass,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName,BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anEndPoint,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_operationTemplates) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_aRoleAttribute=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_roleGetter=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_roleSetter=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_orderedSetType=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_setType=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_theRoleName=null;
BMTL_theRoleName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)BMTL_anEndPoint.get_BMTL_roleName();
BMTL_aRoleAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)((BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Attribute"})).instanciate()));
BMTL_aRoleAttribute.set_BMTL_name(BMTL_theRoleName);
BooleanValue GenSymbol40 = (BooleanValue)BMTL_anEndPoint.get_BMTL_isOrdered();
if (GenSymbol40.getTheBoolean()) {
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedSetType.BMTL_append(new BMTLString("Standard"));
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedSetType.BMTL_append(new BMTLString("OrderedSet"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_aRoleAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_orderedSetType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_aRoleAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_roleGetter=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("getOrderedASSOCIATIONNAMEROLENAME"),BMTL_operationTemplates);
theCaller.BMTL_templateGetter(BMTL_roleGetter,BMTL_associationName,BMTL_theRoleName,BMTLBoolean.TRUE);
BMTL_roleSetter=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("setOrderedASSOCIATIONNAMEROLENAME"),BMTL_operationTemplates);
theCaller.BMTL_templateSetter(BMTL_roleSetter,BMTL_associationName,BMTL_theRoleName,BMTLBoolean.TRUE);
} else {
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_setType.BMTL_append(new BMTLString("Standard"));
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_setType.BMTL_append(new BMTLString("Set"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_aRoleAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_setType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_aRoleAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_roleGetter=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("getASSOCIATIONNAMEROLENAME"),BMTL_operationTemplates);
theCaller.BMTL_templateGetter(BMTL_roleGetter,BMTL_associationName,BMTL_theRoleName,BMTLBoolean.FALSE);
BMTL_roleSetter=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("setASSOCIATIONNAMEROLENAME"),BMTL_operationTemplates);
theCaller.BMTL_templateSetter(BMTL_roleSetter,BMTL_associationName,BMTL_theRoleName,BMTLBoolean.FALSE);
}
BMTL_aRoleAttribute.set_BMTL_possibleGetter(BMTL_roleGetter);
BMTL_roleGetter.set_BMTL_isGetterFor(BMTL_aRoleAttribute);
BMTL_aRoleAttribute.set_BMTL_possibleSetter(BMTL_roleSetter);
BMTL_roleSetter.set_BMTL_isSetterFor(BMTL_aRoleAttribute);
BMTL_aClass.BMTL_appendDefinedAttributes(BMTL_aRoleAttribute);
BMTL_aClass.BMTL_appendDefinedMethods(BMTL_roleGetter);
BMTL_aClass.BMTL_appendDefinedMethods(BMTL_roleSetter);
return BMTLVoid.TheInstance; }


public BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_makeRoleAttribute(BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anEndPoint) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_anAssociationAttribute=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_orderedSetType=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_setType=null;
BMTL_anAssociationAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)((BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Attribute"})).instanciate()));
BMTL_anAssociationAttribute.set_BMTL_name(BMTL_anEndPoint.get_BMTL_roleName());
BooleanValue GenSymbol41 = (BooleanValue)BMTL_anEndPoint.get_BMTL_isOrdered();
if (GenSymbol41.getTheBoolean()) {
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedSetType.BMTL_append(new BMTLString("Standard"));
BMTL_orderedSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedSetType.BMTL_append(new BMTLString("OrderedSet"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_anAssociationAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_orderedSetType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_anAssociationAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
} else {
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_setType.BMTL_append(new BMTLString("Standard"));
BMTL_setType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_setType.BMTL_append(new BMTLString("Set"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_anAssociationAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_setType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_anAssociationAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
}
return (BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(BMTL_anAssociationAttribute);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_linkTheUserClass(BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anEndPoint,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_userClasses,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_otherEndPoints,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_operationTemplates) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_associationNameType=null;
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_theAssociationRefAttribute=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theknownClasses=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theOtherEnds=null;
BasicMtlASTWithAssociationView.BMTL_UserClassInterface BMTL_aClass=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_setLink=null;
BMTL_associationNameType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_associationNameType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_associationNameType.BMTL_append(BMTL_associationName);
BMTL_theAssociationRefAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)((BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Attribute"})).instanciate()));
BMTL_theAssociationRefAttribute.set_BMTL_name(BMTL_associationName);
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theAssociationRefAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_associationNameType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theAssociationRefAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_theknownClasses=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_userClasses.BMTL_getNewIterator();
BMTL_theknownClasses.BMTL_start();
BooleanValue GenSymbol42;
do {GenSymbol42 = (BooleanValue)BMTL_theknownClasses.BMTL_isOn();
if (GenSymbol42.getTheBoolean()) {
BMTL_aClass=(BasicMtlASTWithAssociationView.BMTL_UserClassInterface)((BasicMtlASTWithAssociationView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(BMTL_theknownClasses.BMTL_item()));
BooleanValue GenSymbol43 = (BooleanValue)BMTL_aClass.get_BMTL_name().BMTL__3d(BMTL_anEndPoint.get_BMTL_className());
if (GenSymbol43.getTheBoolean()) {
BMTL_aClass.BMTL_appendDefinedAttributes(BMTL_theAssociationRefAttribute);
BMTL_theOtherEnds=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_otherEndPoints.BMTL_getNewIterator();
BMTL_theOtherEnds.BMTL_start();
BooleanValue GenSymbol44;
do {GenSymbol44 = (BooleanValue)BMTL_theOtherEnds.BMTL_isOn();
if (GenSymbol44.getTheBoolean()) {
theCaller.BMTL_makeRoleGetterSetter(BMTL_aClass,BMTL_associationName,((BasicMtlASTWithAssociationView.BMTL_EndPointInterface)CommonFunctions.toBMTLDataType(BMTL_theOtherEnds.BMTL_item())),BMTL_operationTemplates);
BMTL_theOtherEnds.BMTL_next();
}

} while (GenSymbol44.getTheBoolean());
BMTL_setLink=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("ASSOCIATIONNAMEsetLink"),BMTL_operationTemplates);
theCaller.BMTL_templateSetLink(BMTL_setLink,BMTL_associationName);
BMTL_aClass.BMTL_appendDefinedMethods(BMTL_setLink);
}
BMTL_theknownClasses.BMTL_next();
}

} while (GenSymbol42.getTheBoolean());
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_walkEndPoints(BasicMtlASTWithAssociationView.BMTL_UserClassInterface BMTL_theAssociationClass,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_userClasses,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_operationTemplates) throws Throwable {
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_anAssociationAttribute=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_lowerMInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_upperMInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_isOrderedInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_getterInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_orderedGetterInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_setterInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_orderedSetterInstructions=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_lowerMultiplicityOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_upperMultiplicityOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_isOrderedOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_getterOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_orderedGetterOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_setterOp=null;
BasicMtlASTWithAssociationView.BMTL_OperationInterface BMTL_orderedSetterOp=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_lowerMInstr=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_upperMInstr=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_isOrderedTrueInstr=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_isOrderedFalseInstr=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_getterInstr=null;
BasicMtlASTWithAssociationView.BMTL_IfInterface BMTL_setterInstr=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_isOrderedOpinstr3=null;
BasicMtlASTWithAssociationView.BMTL_InstructionInterface BMTL_isOrderedOpinstr4=null;
BasicMtlASTWithAssociationView.BMTL_ReturnInterface BMTL_returnNullInstr=null;
BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anEndPoint=null;
BasicMtlASTWithAssociationView.BMTL_EndPointInterface BMTL_anotherEndPoint=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theEndPoints=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theOtherEndPoints=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_otherEndPoints=null;
BMTL_lowerMInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_upperMInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_isOrderedInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_getterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_orderedGetterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_setterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_orderedSetterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_lowerMultiplicityOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("lowerMultiplicity"),BMTL_operationTemplates);
BMTL_upperMultiplicityOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("upperMultiplicity"),BMTL_operationTemplates);
BMTL_isOrderedOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("isOrdered"),BMTL_operationTemplates);
BMTL_isOrderedOpinstr3=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_isOrderedOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(3));
BMTL_isOrderedOpinstr4=(BasicMtlASTWithAssociationView.BMTL_InstructionInterface)BMTL_isOrderedOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(4));
BMTL_getterOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("get"),BMTL_operationTemplates);
BMTL_orderedGetterOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("orderedGet"),BMTL_operationTemplates);
BMTL_setterOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("set"),BMTL_operationTemplates);
BMTL_orderedSetterOp=(BasicMtlASTWithAssociationView.BMTL_OperationInterface)theCaller.BMTL_getNamedOperation(new BMTLString("orderedSet"),BMTL_operationTemplates);
BMTL_lowerMInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_lowerMultiplicityOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(1))));
BMTL_upperMInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_upperMultiplicityOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(1))));
BMTL_isOrderedTrueInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_isOrderedOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(1))));
BMTL_isOrderedFalseInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_isOrderedOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(2))));
BMTL_getterInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_getterOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(1))));
BMTL_returnNullInstr=(BasicMtlASTWithAssociationView.BMTL_ReturnInterface)((BasicMtlASTWithAssociationView.BMTL_ReturnInterface)CommonFunctions.toBMTLDataType(BMTL_getterOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(2))));
BMTL_setterInstr=(BasicMtlASTWithAssociationView.BMTL_IfInterface)((BasicMtlASTWithAssociationView.BMTL_IfInterface)CommonFunctions.toBMTLDataType(BMTL_setterOp.get_BMTL_instructions().BMTL_at(new BMTLInteger(1))));
BMTL_theEndPoints=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_endPoints().BMTL_getNewIterator();
BMTL_theEndPoints.BMTL_start();
BooleanValue GenSymbol45;
do {GenSymbol45 = (BooleanValue)BMTL_theEndPoints.BMTL_isOn();
if (GenSymbol45.getTheBoolean()) {
BMTL_anEndPoint=(BasicMtlASTWithAssociationView.BMTL_EndPointInterface)((BasicMtlASTWithAssociationView.BMTL_EndPointInterface)CommonFunctions.toBMTLDataType(BMTL_theEndPoints.BMTL_item()));
BMTL_anAssociationAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)theCaller.BMTL_makeRoleAttribute(BMTL_anEndPoint);
BMTL_theAssociationClass.BMTL_appendDefinedAttributes(BMTL_anAssociationAttribute);
BMTL_lowerMInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_lowerMInstructions.BMTL_including(theCaller.BMTL_templateMultiplicityInstr(BMTL_lowerMInstr,BMTL_anEndPoint.get_BMTL_roleName(),BMTL_anEndPoint.get_BMTL_multiplicity().get_BMTL_lowerBound()));
BMTL_upperMInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_upperMInstructions.BMTL_including(theCaller.BMTL_templateMultiplicityInstr(BMTL_upperMInstr,BMTL_anEndPoint.get_BMTL_roleName(),BMTL_anEndPoint.get_BMTL_multiplicity().get_BMTL_upperBound()));
BooleanValue GenSymbol46 = (BooleanValue)BMTL_anEndPoint.get_BMTL_isOrdered();
if (GenSymbol46.getTheBoolean()) {
BMTL_isOrderedInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_isOrderedInstructions.BMTL_including(theCaller.BMTL_templateIsOrderedInstr(BMTL_isOrderedTrueInstr,BMTL_anEndPoint.get_BMTL_roleName()));
BMTL_orderedGetterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedGetterInstructions.BMTL_including(theCaller.BMTL_templateGetInstr(BMTL_getterInstr,BMTL_anEndPoint.get_BMTL_roleName()));
BMTL_orderedSetterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_orderedSetterInstructions.BMTL_including(theCaller.BMTL_templateSetInstr(BMTL_setterInstr,BMTL_anEndPoint.get_BMTL_roleName()));
} else {
BMTL_isOrderedInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_isOrderedInstructions.BMTL_including(theCaller.BMTL_templateIsOrderedInstr(BMTL_isOrderedFalseInstr,BMTL_anEndPoint.get_BMTL_roleName()));
BMTL_getterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_getterInstructions.BMTL_including(theCaller.BMTL_templateGetInstr(BMTL_getterInstr,BMTL_anEndPoint.get_BMTL_roleName()));
BMTL_setterInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_setterInstructions.BMTL_including(theCaller.BMTL_templateSetInstr(BMTL_setterInstr,BMTL_anEndPoint.get_BMTL_roleName()));
}
BMTL_otherEndPoints=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate()));
BMTL_theOtherEndPoints=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_endPoints().BMTL_getNewIterator();
BMTL_theOtherEndPoints.BMTL_start();
BooleanValue GenSymbol47;
do {GenSymbol47 = (BooleanValue)BMTL_theOtherEndPoints.BMTL_isOn();
if (GenSymbol47.getTheBoolean()) {
BMTL_anotherEndPoint=(BasicMtlASTWithAssociationView.BMTL_EndPointInterface)((BasicMtlASTWithAssociationView.BMTL_EndPointInterface)CommonFunctions.toBMTLDataType(BMTL_theOtherEndPoints.BMTL_item()));
BooleanValue GenSymbol48 = (BooleanValue)BMTL_anotherEndPoint.get_BMTL_roleName().BMTL__3d(BMTL_anEndPoint.get_BMTL_roleName()).BMTL_not();
if (GenSymbol48.getTheBoolean()) {
BMTL_otherEndPoints=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)BMTL_otherEndPoints.BMTL_including(BMTL_anotherEndPoint);
}
BMTL_theOtherEndPoints.BMTL_next();
}

} while (GenSymbol47.getTheBoolean());
theCaller.BMTL_linkTheUserClass(BMTL_anEndPoint,BMTL_associationName,BMTL_userClasses,BMTL_otherEndPoints,BMTL_operationTemplates);
BMTL_theEndPoints.BMTL_next();
}

} while (GenSymbol45.getTheBoolean());
BMTL_lowerMultiplicityOp.set_BMTL_instructions(BMTL_lowerMInstructions);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_lowerMultiplicityOp);
BMTL_upperMultiplicityOp.set_BMTL_instructions(BMTL_upperMInstructions);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_upperMultiplicityOp);
BMTL_isOrderedInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_isOrderedInstructions.BMTL_including(BMTL_isOrderedOpinstr3);
BMTL_isOrderedInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_isOrderedInstructions.BMTL_including(BMTL_isOrderedOpinstr4);
BMTL_isOrderedOp.set_BMTL_instructions(BMTL_isOrderedInstructions);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_isOrderedOp);
BMTL_getterOp.set_BMTL_instructions(BMTL_getterInstructions);
BMTL_getterOp.set_BMTL_instructions(BMTL_getterOp.get_BMTL_instructions().BMTL_including(BMTL_returnNullInstr));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_getterOp);
BMTL_orderedGetterOp.set_BMTL_instructions(BMTL_orderedGetterInstructions);
BMTL_orderedGetterOp.set_BMTL_instructions(BMTL_orderedGetterOp.get_BMTL_instructions().BMTL_including(BMTL_returnNullInstr));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_orderedGetterOp);
BMTL_setterOp.set_BMTL_instructions(BMTL_setterInstructions);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_setterOp);
BMTL_orderedSetterOp.set_BMTL_instructions(BMTL_orderedSetterInstructions);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(BMTL_orderedSetterOp);
return BMTLVoid.TheInstance; }


public BasicMtlASTWithAssociationView.BMTL_UserClassInterface BMTL_transformAssociation(BasicMtlASTWithAssociationView.BMTL_LibraryInterface BMTL_templatesLib,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_userClasses) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_aSetType=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_aBooleanType=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_operationTemplates=null;
BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface BMTL_node=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTWithAssociationView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_associationName=null;
BasicMtlASTWithAssociationView.BMTL_UserClassInterface BMTL_theAssociationClass=null;
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_allLinksAttribute=null;
BasicMtlASTWithAssociationView.BMTL_AttributeInterface BMTL_validityAttribute=null;
BMTL_operationTemplates=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)BMTL_templatesLib.get_BMTL_definedOperations();
BMTL_associationName=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)theCaller.BMTL_makeTheAssociationName();
BMTL_theAssociationClass=(BasicMtlASTWithAssociationView.BMTL_UserClassInterface)((BasicMtlASTWithAssociationView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "UserClass"})).instanciate()));
BMTL_theAssociationClass.set_BMTL_name(BMTL_associationName);
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theAssociationClass)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_aSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_aSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_aSetType.BMTL_append(new BMTLString("Standard"));
BMTL_aSetType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_aSetType.BMTL_append(new BMTLString("Set"));
BMTL_allLinksAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)((BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Attribute"})).instanciate()));
BMTL_allLinksAttribute.set_BMTL_name(new BMTLString("allKnownLinks"));
((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_allLinksAttribute)).getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_aSetType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_allLinksAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_theAssociationClass.BMTL_appendDefinedAttributes(BMTL_allLinksAttribute);
BMTL_aBooleanType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "OrderedSet"})).instanciate()));
BMTL_aBooleanType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_aBooleanType.BMTL_append(new BMTLString("Standard"));
BMTL_aBooleanType=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)BMTL_aBooleanType.BMTL_append(new BMTLString("Boolean"));
BMTL_validityAttribute=(BasicMtlASTWithAssociationView.BMTL_AttributeInterface)((BasicMtlASTWithAssociationView.BMTL_AttributeInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTWithAssociationView", "Attribute"})).instanciate()));
BMTL_validityAttribute.set_BMTL_name(new BMTLString("validity"));
((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_validityAttribute)).getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty(new BMTLString("Type"),BMTL_aBooleanType,new BMTLString("TypeTag"));
((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_validityAttribute)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().BMTL_createNewStringProperty(new BMTLString("LineNumber"),new BMTLString("-200000"),new BMTLString("StringTag"));
BMTL_theAssociationClass.BMTL_appendDefinedAttributes(BMTL_validityAttribute);
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_templateGetRole(theCaller.BMTL_getNamedOperation(new BMTLString("getRole"),BMTL_operationTemplates),BMTL_associationName));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_templateGetRole(theCaller.BMTL_getNamedOperation(new BMTLString("getOrderedRole"),BMTL_operationTemplates),BMTL_associationName));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_templateBuildLink(theCaller.BMTL_getNamedOperation(new BMTLString("buildLink"),BMTL_operationTemplates),BMTL_associationName));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_getNamedOperation(new BMTLString("updateLink"),BMTL_operationTemplates));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_getNamedOperation(new BMTLString("removeLink"),BMTL_operationTemplates));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_templateAssociateOperation(theCaller.BMTL_getNamedOperation(new BMTLString("associateOperation"),BMTL_operationTemplates),BMTL_associationName));
BMTL_theAssociationClass.BMTL_appendDefinedMethods(theCaller.BMTL_templateDissociateOperation(theCaller.BMTL_getNamedOperation(new BMTLString("dissociateOperation"),BMTL_operationTemplates),BMTL_associationName));
theCaller.BMTL_walkEndPoints(BMTL_theAssociationClass,BMTL_associationName,BMTL_userClasses,BMTL_operationTemplates);
BMTL_node=(BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(BMTL_theAssociationClass));
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol49;
do {GenSymbol49 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol49.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTWithAssociationView.BMTL_PropertyInterface)((BasicMtlASTWithAssociationView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
BooleanValue GenSymbol50 = (BooleanValue)theCaller.BMTL_isNull(BMTL_node.get_BMTL_decoration());
if (GenSymbol50.getTheBoolean()) {
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"Standard", "Set"})).instanciate())));
}
BMTL_node.set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(BMTL_node.get_BMTL_decoration().BMTL_including(BMTL_aProperty))));
BMTL_properties.BMTL_next();
}

} while (GenSymbol49.getTheBoolean());
return (BasicMtlASTWithAssociationView.BMTL_UserClassInterface)CommonFunctions.toBMTLDataType(BMTL_theAssociationClass);
}


}
