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

public class BMTL_Try extends BMTLObject implements BMTL_TryInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Try oclAsType(Object o)
{     return (BMTL_Try)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_TryInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Instruction BMTLRef_BMTL_Instruction;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_tryBody;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_finalizeBody;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_catchPart;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Try(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("Try");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::Try",this);
theCaller=this;
theLib.recordNewInstance("Try",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)inheritanceMap.get("BasicMtlASTView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Try(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_TryInterface o)
{ super("Try");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::Try",this);
theLib.recordNewInstance("Try",theCaller);
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
    theLib.removeInstance("Try",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_Try getRef_BMTL_BasicMtlASTView_5fTry()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction()
{ return this.BMTLRef_BMTL_Instruction; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Try"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol486)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol486)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol487)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol487)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol488,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol489,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol490) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol488,GenSymbol489,GenSymbol490)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol491,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol492,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol493) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol491,GenSymbol492,GenSymbol493)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol494,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol495,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol496) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol494,GenSymbol495,GenSymbol496)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol497) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol497)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol498,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol499) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol498,GenSymbol499)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol500) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol500)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_tryBody()
{ return this.BMTL_tryBody; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tryBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_tryBody=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_finalizeBody()
{ return this.BMTL_finalizeBody; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_finalizeBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_finalizeBody=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_catchPart()
{ return this.BMTL_catchPart; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_catchPart (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_catchPart=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendTryBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol501 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_tryBody());
if (GenSymbol501.getTheBoolean()) {
BMTL_tryBody=new BMTLOrderedSet();
}
set_BMTL_tryBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_tryBody().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendCatchPart(BasicMtlASTView.BMTL_CatchInterface BMTL_aCatch) throws Throwable {
BooleanValue GenSymbol502 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_catchPart());
if (GenSymbol502.getTheBoolean()) {
BMTL_catchPart=new BMTLOrderedSet();
}
set_BMTL_catchPart(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_catchPart().BMTL_append(BMTL_aCatch))));
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendFinalizeBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol503 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_finalizeBody());
if (GenSymbol503.getTheBoolean()) {
BMTL_finalizeBody=new BMTLOrderedSet();
}
set_BMTL_finalizeBody(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_finalizeBody().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
BasicMtlASTView.BMTL_InstructionInterface BMTL_anInstruction=null;
BasicMtlASTView.BMTL_CatchInterface BMTL_aCatch=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_instructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_catches=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Try theTry=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Try();
BMTL_instructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_tryBody().BMTL_getNewIterator();
BMTL_instructions.BMTL_start();
BooleanValue GenSymbol504;
do {GenSymbol504 = (BooleanValue)BMTL_instructions.BMTL_isOn();
if (GenSymbol504.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_instructions.BMTL_item()));
theTry.appendTryBody((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_instructions.BMTL_next();
}

} while (GenSymbol504.getTheBoolean());
BMTL_catches=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_catchPart().BMTL_getNewIterator();
BMTL_catches.BMTL_start();
BooleanValue GenSymbol505;
do {GenSymbol505 = (BooleanValue)BMTL_catches.BMTL_isOn();
if (GenSymbol505.getTheBoolean()) {
BMTL_aCatch=(BasicMtlASTView.BMTL_CatchInterface)((BasicMtlASTView.BMTL_CatchInterface)CommonFunctions.toBMTLDataType(BMTL_catches.BMTL_item()));
theTry.appendCatchPart((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch)BMTL_aCatch.BMTL_toASTJava());
BMTL_catches.BMTL_next();
}

} while (GenSymbol505.getTheBoolean());
BMTL_instructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_finalizeBody().BMTL_getNewIterator();
BMTL_instructions.BMTL_start();
BooleanValue GenSymbol506;
do {GenSymbol506 = (BooleanValue)BMTL_instructions.BMTL_isOn();
if (GenSymbol506.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_instructions.BMTL_item()));
theTry.appendFinalizeBody((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_instructions.BMTL_next();
}

} while (GenSymbol506.getTheBoolean());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol507;
do {GenSymbol507 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol507.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theTry.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol507.getTheBoolean());
return theTry;
}


}
