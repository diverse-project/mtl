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

public class BMTL_While extends BMTLObject implements BMTL_WhileInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_While oclAsType(Object o)
{     return (BMTL_While)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_WhileInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Instruction BMTLRef_BMTL_Instruction;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
BasicMtlASTView.BMTL_ExpressionInterface BMTL_condition;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_body;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_While(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("While");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::While",this);
theCaller=this;
theLib.recordNewInstance("While",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)inheritanceMap.get("BasicMtlASTView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_While(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_WhileInterface o)
{ super("While");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::While",this);
theLib.recordNewInstance("While",theCaller);
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
    theLib.removeInstance("While",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_While getRef_BMTL_BasicMtlASTView_5fWhile()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction()
{ return this.BMTLRef_BMTL_Instruction; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"While"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol605)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol605)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol606)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol606)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol607,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol608,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol609) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol607,GenSymbol608,GenSymbol609)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol610,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol611,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol612) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol610,GenSymbol611,GenSymbol612)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol613,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol614,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol615) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol613,GenSymbol614,GenSymbol615)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol616) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol616)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol617,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol618) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol617,GenSymbol618)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol619) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol619)); }

public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_condition()
{ return this.BMTL_condition; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_condition (BasicMtlASTView.BMTL_ExpressionInterface value)
{ this.BMTL_condition=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_body()
{ return this.BMTL_body; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_body (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_body=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable {
BooleanValue GenSymbol620 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_body());
if (GenSymbol620.getTheBoolean()) {
BMTL_body=new BMTLOrderedSet();
}
set_BMTL_body(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_body().BMTL_append(BMTL_instr))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
BasicMtlASTView.BMTL_InstructionInterface BMTL_anInstruction=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_instructions=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.While theWhile=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.While((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)BMTL_condition.BMTL_toASTJava());
BMTL_instructions=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_body().BMTL_getNewIterator();
BMTL_instructions.BMTL_start();
BooleanValue GenSymbol621;
do {GenSymbol621 = (BooleanValue)BMTL_instructions.BMTL_isOn();
if (GenSymbol621.getTheBoolean()) {
BMTL_anInstruction=(BasicMtlASTView.BMTL_InstructionInterface)((BasicMtlASTView.BMTL_InstructionInterface)CommonFunctions.toBMTLDataType(BMTL_instructions.BMTL_item()));
theWhile.appendBody((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)BMTL_anInstruction.BMTL_toASTJava());
BMTL_instructions.BMTL_next();
}

} while (GenSymbol621.getTheBoolean());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol622;
do {GenSymbol622 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol622.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theWhile.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol622.getTheBoolean());
return theWhile;
}


}
