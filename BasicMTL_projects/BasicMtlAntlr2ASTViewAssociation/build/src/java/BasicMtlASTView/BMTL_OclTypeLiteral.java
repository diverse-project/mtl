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

public class BMTL_OclTypeLiteral extends BMTLObject implements BMTL_OclTypeLiteralInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_OclTypeLiteral oclAsType(Object o)
{     return (BMTL_OclTypeLiteral)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_OclTypeLiteralInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Literal BMTLRef_BMTL_Literal;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_typeValue;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_OclTypeLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("OclTypeLiteral");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::OclTypeLiteral",this);
theCaller=this;
theLib.recordNewInstance("OclTypeLiteral",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)inheritanceMap.get("BasicMtlASTView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_OclTypeLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_OclTypeLiteralInterface o)
{ super("OclTypeLiteral");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::OclTypeLiteral",this);
theLib.recordNewInstance("OclTypeLiteral",theCaller);
if (map.containsKey("BasicMtlASTView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)map.get("BasicMtlASTView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fLiteral().delete();
    theLib.removeInstance("OclTypeLiteral",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_OclTypeLiteral getRef_BMTL_BasicMtlASTView_5fOclTypeLiteral()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Literal getRef_BMTL_BasicMtlASTView_5fLiteral()
{ return this.BMTLRef_BMTL_Literal; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"OclTypeLiteral"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTView_5fLiteral().getRef_BMTL_BasicMtlASTView_5fExpression ()); }

public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fExpression().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol309)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol309)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol310)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol310)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol311,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol312,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol313) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol311,GenSymbol312,GenSymbol313)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol314,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol315,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol316) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol314,GenSymbol315,GenSymbol316)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol317,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol318,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol319) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol317,GenSymbol318,GenSymbol319)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol320) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol320)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol321,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol322) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol321,GenSymbol322)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol323) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol323)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_typeValue()
{ return this.BMTL_typeValue; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_typeValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value)
{ this.BMTL_typeValue=value;
return BMTLVoid.TheInstance; }

public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_typeValues=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_aTypeValue=null;
	java.util.Vector qualifiedName=new java.util.Vector();
BMTL_typeValues=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)theCaller.get_BMTL_typeValue().BMTL_getNewIterator();
BMTL_typeValues.BMTL_start();
BooleanValue GenSymbol324;
do {GenSymbol324 = (BooleanValue)BMTL_typeValues.BMTL_isOn();
if (GenSymbol324.getTheBoolean()) {
BMTL_aTypeValue=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_typeValues.BMTL_item()));
qualifiedName.addElement(BMTL_aTypeValue.getTheString());
BMTL_typeValues.BMTL_next();
}

} while (GenSymbol324.getTheBoolean());
return new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OclTypeLiteral(qualifiedName);
}


}
