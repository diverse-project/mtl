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

public class BMTL_If extends BMTLObject implements BMTL_IfInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_If oclAsType(Object o)
{     return (BMTL_If)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_IfInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Instruction BMTLRef_BMTL_Instruction;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
BasicMtlASTView.BMTL_ExpressionInterface BMTL_condition;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_thenBody;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_elseBody;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_If(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("If");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::If",this);
theCaller=this;
theLib.recordNewInstance("If",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)inheritanceMap.get("BasicMtlASTView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_If(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_IfInterface o)
{ super("If");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::If",this);
theLib.recordNewInstance("If",theCaller);
if (map.containsKey("BasicMtlASTView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)map.get("BasicMtlASTView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fInstruction().delete();
    theLib.removeInstance("If",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_If getRef_BMTL_BasicMtlASTView_5fIf()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction()
{ return this.BMTLRef_BMTL_Instruction; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"If"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol134)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol134)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol135)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol135)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol136,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol137,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol138) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol136,GenSymbol137,GenSymbol138)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol139,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol140,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol141) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol139,GenSymbol140,GenSymbol141)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol142,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol143,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol144) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol142,GenSymbol143,GenSymbol144)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol145) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol145)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol146,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol147) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol146,GenSymbol147)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol148) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol148)); }

public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_condition()
{ return this.BMTL_condition; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_condition (BasicMtlASTView.BMTL_ExpressionInterface value)
{ this.BMTL_condition=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_thenBody()
{ return this.BMTL_thenBody; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_thenBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_thenBody=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_elseBody()
{ return this.BMTL_elseBody; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_elseBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_elseBody=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendThenBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol149 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_thenBody());
if (GenSymbol149.getTheBoolean()) {
BMTL_thenBody=new BMTLOrderedSet();
}
set_BMTL_thenBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_thenBody().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendElseBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol150 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_elseBody());
if (GenSymbol150.getTheBoolean()) {
BMTL_elseBody=new BMTLOrderedSet();
}
set_BMTL_elseBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_elseBody().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
BasicMtlASTView.BMTL_InstructionInterface BMTL_anInstruction=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_instructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.If theIf=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.If((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)BMTL_condition.BMTL_toASTJava());
BMTL_instructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_thenBody().BMTL_getNewIterator();
BMTL_instructions.BMTL_start();
BooleanValue GenSymbol151;
do {GenSymbol151 = (BooleanValue)BMTL_instructions.BMTL_isOn();
if (GenSymbol151.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_instructions.BMTL_item()));
theIf.appendThenBody((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_instructions.BMTL_next();
}

} while (GenSymbol151.getTheBoolean());
BooleanValue GenSymbol152 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_elseBody()).BMTL_not();
if (GenSymbol152.getTheBoolean()) {
BMTL_instructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_elseBody().BMTL_getNewIterator();
BMTL_instructions.BMTL_start();
BooleanValue GenSymbol153;
do {GenSymbol153 = (BooleanValue)BMTL_instructions.BMTL_isOn();
if (GenSymbol153.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_instructions.BMTL_item()));
theIf.appendElseBody((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_instructions.BMTL_next();
}

} while (GenSymbol153.getTheBoolean());
}
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol154;
do {GenSymbol154 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol154.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theIf.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol154.getTheBoolean());
return theIf;
}


}
