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

public class BMTL_OperationCall extends BMTLObject implements BMTL_OperationCallInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_OperationCall oclAsType(Object o)
{     return (BMTL_OperationCall)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_OperationCallInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Expression BMTLRef_BMTL_Expression;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_arguments;

BasicMtlASTView.BMTL_ExpressionInterface BMTL_caller;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_OperationCall(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("OperationCall");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::OperationCall",this);
theCaller=this;
theLib.recordNewInstance("OperationCall",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Expression")) 
	BMTLRef_BMTL_Expression= (BMTL_Expression)inheritanceMap.get("BasicMtlASTView::Expression");
else BMTLRef_BMTL_Expression= new BMTL_Expression(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_OperationCall(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_OperationCallInterface o)
{ super("OperationCall");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::OperationCall",this);
theLib.recordNewInstance("OperationCall",theCaller);
if (map.containsKey("BasicMtlASTView::Expression")) 
	BMTLRef_BMTL_Expression= (BMTL_Expression)map.get("BasicMtlASTView::Expression");
else BMTLRef_BMTL_Expression= new BMTL_Expression(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fExpression().delete();
    theLib.removeInstance("OperationCall",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_OperationCall getRef_BMTL_BasicMtlASTView_5fOperationCall()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression()
{ return this.BMTLRef_BMTL_Expression; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"OperationCall"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fExpression().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol350)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol350)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol351)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol351)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol352,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol353,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol354) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol352,GenSymbol353,GenSymbol354)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol355,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol356,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol357) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol355,GenSymbol356,GenSymbol357)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol358,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol359,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol360) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol358,GenSymbol359,GenSymbol360)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol361) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol361)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol362,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol363) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol362,GenSymbol363)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol364) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol364)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_arguments()
{ return this.BMTL_arguments; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_arguments (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_arguments=value;
return BMTLVoid.TheInstance; }

public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_caller()
{ return this.BMTL_caller; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_caller (BasicMtlASTView.BMTL_ExpressionInterface value)
{ this.BMTL_caller=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendArguments(BasicMtlASTView.BMTL_ExpressionInterface BMTL_arg) throws Throwable {
BooleanValue GenSymbol365 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_arguments());
if (GenSymbol365.getTheBoolean()) {
BMTL_arguments=new BMTLOrderedSet();
}
set_BMTL_arguments(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_arguments().BMTL_append(BMTL_arg))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theArguments=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_ExpressionInterface BMTL_anArgument=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OperationCall theOperationCall=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OperationCall(this.BMTL_name.getTheString());
BooleanValue GenSymbol366 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_arguments()).BMTL_not();
if (GenSymbol366.getTheBoolean()) {
BMTL_theArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_arguments().BMTL_getNewIterator();
BMTL_theArguments.BMTL_start();
BooleanValue GenSymbol367;
do {GenSymbol367 = (BooleanValue)BMTL_theArguments.BMTL_isOn();
if (GenSymbol367.getTheBoolean()) {
BMTL_anArgument=(BasicMtlASTView.BMTL_ExpressionInterface)((BasicMtlASTView.BMTL_ExpressionInterface)CommonFunctions.toBMTLDataType(BMTL_theArguments.BMTL_item()));
theOperationCall.appendArguments((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)BMTL_anArgument.BMTL_toASTJava());
BMTL_theArguments.BMTL_next();
}

} while (GenSymbol367.getTheBoolean());
}
theOperationCall.setCaller((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)BMTL_caller.BMTL_toASTJava());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol368;
do {GenSymbol368 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol368.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theOperationCall.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol368.getTheBoolean());
return theOperationCall;
}


}
