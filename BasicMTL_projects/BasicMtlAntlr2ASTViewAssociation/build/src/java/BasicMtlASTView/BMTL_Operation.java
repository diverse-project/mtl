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

public class BMTL_Operation extends BMTLObject implements BMTL_OperationInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Operation oclAsType(Object o)
{     return (BMTL_Operation)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_OperationInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_instructions;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_Parameters;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_declaredVariables;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_throwsExceptionValue;

BasicMtlASTView.BMTL_AttributeInterface BMTL_isGetterFor;

BasicMtlASTView.BMTL_AttributeInterface BMTL_isSetterFor;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Operation(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("Operation");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::Operation",this);
theCaller=this;
theLib.recordNewInstance("Operation",this);

if (inheritanceMap.containsKey("BasicMtlASTView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Operation(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_OperationInterface o)
{ super("Operation");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::Operation",this);
theLib.recordNewInstance("Operation",theCaller);
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
    theLib.removeInstance("Operation",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_Operation getRef_BMTL_BasicMtlASTView_5fOperation()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Operation"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol325)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol325)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol326,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol327,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol328) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol326,GenSymbol327,GenSymbol328)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol329,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol330,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol331) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol329,GenSymbol330,GenSymbol331)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol332,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol333,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol334) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol332,GenSymbol333,GenSymbol334)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol335) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol335)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol336,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol337) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol336,GenSymbol337)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol338) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol338)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_instructions()
{ return this.BMTL_instructions; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_instructions (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_instructions=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_Parameters()
{ return this.BMTL_Parameters; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_Parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_Parameters=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_declaredVariables()
{ return this.BMTL_declaredVariables; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_declaredVariables (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_declaredVariables=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_throwsExceptionValue()
{ return this.BMTL_throwsExceptionValue; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_throwsExceptionValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value)
{ this.BMTL_throwsExceptionValue=value;
return BMTLVoid.TheInstance; }

public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isGetterFor()
{ return this.BMTL_isGetterFor; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isGetterFor (BasicMtlASTView.BMTL_AttributeInterface value)
{ this.BMTL_isGetterFor=value;
return BMTLVoid.TheInstance; }

public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isSetterFor()
{ return this.BMTL_isSetterFor; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isSetterFor (BasicMtlASTView.BMTL_AttributeInterface value)
{ this.BMTL_isSetterFor=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters(BasicMtlASTView.BMTL_VarDeclarationInterface BMTL_param) throws Throwable {
BooleanValue GenSymbol339 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_Parameters());
if (GenSymbol339.getTheBoolean()) {
BMTL_Parameters=new BMTLOrderedSet();
}
set_BMTL_Parameters(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_Parameters().BMTL_append(BMTL_param))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDeclaredVariables(BasicMtlASTView.BMTL_VarDeclarationInterface BMTL_var) throws Throwable {
BooleanValue GenSymbol340 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_declaredVariables());
if (GenSymbol340.getTheBoolean()) {
BMTL_declaredVariables=new BMTLOrderedSet();
}
set_BMTL_declaredVariables(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_declaredVariables().BMTL_append(BMTL_var))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendInstructions(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol341 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_instructions());
if (GenSymbol341.getTheBoolean()) {
BMTL_instructions=new BMTLOrderedSet();
}
set_BMTL_instructions(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_instructions().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
BasicMtlASTView.BMTL_VarDeclarationInterface BMTL_aVarDecl=null;
BasicMtlASTView.BMTL_InstructionInterface BMTL_anInstruction=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theParameters=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_localVars=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theInstructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation theOperation=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation(BMTL_name.getTheString());
BooleanValue GenSymbol342 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_Parameters()).BMTL_not();
if (GenSymbol342.getTheBoolean()) {
BMTL_theParameters=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_Parameters().BMTL_getNewIterator();
BMTL_theParameters.BMTL_start();
BooleanValue GenSymbol343;
do {GenSymbol343 = (BooleanValue)BMTL_theParameters.BMTL_isOn();
if (GenSymbol343.getTheBoolean()) {
BMTL_aVarDecl=(BasicMtlASTView.BMTL_VarDeclarationInterface)((BasicMtlASTView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_theParameters.BMTL_item()));
theOperation.appendParameters((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration)BMTL_aVarDecl.BMTL_toASTJava());
BMTL_theParameters.BMTL_next();
}

} while (GenSymbol343.getTheBoolean());
}
BooleanValue GenSymbol344 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_declaredVariables()).BMTL_not();
if (GenSymbol344.getTheBoolean()) {
BMTL_theParameters=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_declaredVariables().BMTL_getNewIterator();
BMTL_theParameters.BMTL_start();
BooleanValue GenSymbol345;
do {GenSymbol345 = (BooleanValue)BMTL_theParameters.BMTL_isOn();
if (GenSymbol345.getTheBoolean()) {
BMTL_aVarDecl=(BasicMtlASTView.BMTL_VarDeclarationInterface)((BasicMtlASTView.BMTL_VarDeclarationInterface)CommonFunctions.toBMTLDataType(BMTL_theParameters.BMTL_item()));
theOperation.appendDeclaredVariables((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarDeclaration)BMTL_aVarDecl.BMTL_toASTJava());
BMTL_theParameters.BMTL_next();
}

} while (GenSymbol345.getTheBoolean());
}
BooleanValue GenSymbol346 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_instructions()).BMTL_not();
if (GenSymbol346.getTheBoolean()) {
BMTL_theInstructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_instructions().BMTL_getNewIterator();
BMTL_theInstructions.BMTL_start();
BooleanValue GenSymbol347;
do {GenSymbol347 = (BooleanValue)BMTL_theInstructions.BMTL_isOn();
if (GenSymbol347.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_theInstructions.BMTL_item()));
theOperation.appendInstructions((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_theInstructions.BMTL_next();
}

} while (GenSymbol347.getTheBoolean());
}
BooleanValue GenSymbol348 = (BooleanValue)get_BMTL_throwsExceptionValue();
if (GenSymbol348.getTheBoolean()) {
theOperation.setThrowsException(true);
}
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol349;
do {GenSymbol349 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol349.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theOperation.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol349.getTheBoolean());
return theOperation;
}


}
