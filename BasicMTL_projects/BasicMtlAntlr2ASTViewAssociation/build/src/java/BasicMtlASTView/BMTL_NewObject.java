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

public class BMTL_NewObject extends BMTLObject implements BMTL_NewObjectInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_NewObject oclAsType(Object o)
{     return (BMTL_NewObject)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_NewObjectInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Expression BMTLRef_BMTL_Expression;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_arguments;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_NewObject(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("NewObject");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::NewObject",this);
theCaller=this;
theLib.recordNewInstance("NewObject",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Expression")) 
	BMTLRef_BMTL_Expression= (BMTL_Expression)inheritanceMap.get("BasicMtlASTView::Expression");
else BMTLRef_BMTL_Expression= new BMTL_Expression(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_NewObject(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_NewObjectInterface o)
{ super("NewObject");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::NewObject",this);
theLib.recordNewInstance("NewObject",theCaller);
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
    theLib.removeInstance("NewObject",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_NewObject getRef_BMTL_BasicMtlASTView_5fNewObject()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression()
{ return this.BMTLRef_BMTL_Expression; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"NewObject"});
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

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol255)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol255)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol256)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol256)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol257,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol258,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol259) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol257,GenSymbol258,GenSymbol259)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol260,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol261,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol262) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol260,GenSymbol261,GenSymbol262)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol263,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol264,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol265) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol263,GenSymbol264,GenSymbol265)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol266) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol266)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol267,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol268) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol267,GenSymbol268)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol269) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol269)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_arguments()
{ return this.BMTL_arguments; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_arguments (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_arguments=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendArguments(BasicMtlASTView.BMTL_ExpressionInterface BMTL_arg) throws Throwable {
BooleanValue GenSymbol270 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_arguments());
if (GenSymbol270.getTheBoolean()) {
BMTL_arguments=new BMTLOrderedSet();
}
set_BMTL_arguments(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_arguments().BMTL_append(BMTL_arg))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theArguments=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_ExpressionInterface BMTL_anArgument=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.NewObject theNewObject=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.NewObject();
BooleanValue GenSymbol271 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_arguments()).BMTL_not();
if (GenSymbol271.getTheBoolean()) {
BMTL_theArguments=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_arguments().BMTL_getNewIterator();
BMTL_theArguments.BMTL_start();
BooleanValue GenSymbol272;
do {GenSymbol272 = (BooleanValue)BMTL_theArguments.BMTL_isOn();
if (GenSymbol272.getTheBoolean()) {
BMTL_anArgument=(BasicMtlASTView.BMTL_ExpressionInterface)((BasicMtlASTView.BMTL_ExpressionInterface)CommonFunctions.toBMTLDataType(BMTL_theArguments.BMTL_item()));
theNewObject.appendArguments((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)BMTL_anArgument.BMTL_toASTJava());
BMTL_theArguments.BMTL_next();
}

} while (GenSymbol272.getTheBoolean());
}
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol273;
do {GenSymbol273 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol273.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theNewObject.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol273.getTheBoolean());
return theNewObject;
}


}
