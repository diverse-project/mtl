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

public class BMTL_OclAsType extends BMTLObject implements BMTL_OclAsTypeInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_OclAsType oclAsType(Object o)
{     return (BMTL_OclAsType)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_OclAsTypeInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_OclAsType BMTLRef_BMTL_OclAsType;
private BasicMtlASTWithAssociationView.BMTL_Expression BMTLRef_BMTL_Expression;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_OclAsType(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("OclAsType");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::OclAsType",this);
theCaller=this;
theLib.recordNewInstance("OclAsType",this);

if (inheritanceMap.containsKey("BasicMtlASTView::OclAsType"))
	BMTLRef_BMTL_OclAsType= (BasicMtlASTView.BMTL_OclAsType)inheritanceMap.get("BasicMtlASTView::OclAsType");
else BMTLRef_BMTL_OclAsType= new BasicMtlASTView.BMTL_OclAsType((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Expression")) 
	BMTLRef_BMTL_Expression= (BMTL_Expression)inheritanceMap.get("BasicMtlASTWithAssociationView::Expression");
else BMTLRef_BMTL_Expression= new BMTL_Expression(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_OclAsType(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_OclAsTypeInterface o)
{ super("OclAsType");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::OclAsType",this);
theLib.recordNewInstance("OclAsType",theCaller);
if (map.containsKey("BasicMtlASTView::OclAsType"))
	BMTLRef_BMTL_OclAsType= (BasicMtlASTView.BMTL_OclAsType)map.get("BasicMtlASTView::OclAsType");
else BMTLRef_BMTL_OclAsType= new BasicMtlASTView.BMTL_OclAsType((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::Expression")) 
	BMTLRef_BMTL_Expression= (BMTL_Expression)map.get("BasicMtlASTWithAssociationView::Expression");
else BMTLRef_BMTL_Expression= new BMTL_Expression(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fOclAsType().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression().delete();
    theLib.removeInstance("OclAsType",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_OclAsType getRef_BMTL_BasicMtlASTWithAssociationView_5fOclAsType()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_OclAsType getRef_BMTL_BasicMtlASTView_5fOclAsType()
{ return this.BMTLRef_BMTL_OclAsType; }

public BasicMtlASTWithAssociationView.BMTL_Expression getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression()
{ return this.BMTLRef_BMTL_Expression; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"OclAsType"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().getRef_BMTL_BasicMtlASTView_5fExpression ()); }

public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_expression ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_expression ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_expression (BasicMtlASTView.BMTL_ExpressionInterface GenSymbol358)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_expression (GenSymbol358)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_type ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_type ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_type (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol359)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_type (GenSymbol359)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_typeVar ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_typeVar ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_typeVar (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol360)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_typeVar (GenSymbol360)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_methodVar ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_methodVar ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_methodVar (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol361)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_methodVar (GenSymbol361)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_parameterVar ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_parameterVar ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_parameterVar (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol362)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_parameterVar (GenSymbol362)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_isAConstant ()
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().get_BMTL_isAConstant ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isAConstant (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol363)
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().set_BMTL_isAConstant (GenSymbol363)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol364) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().BMTL_appendType (GenSymbol364)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOclAsType().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fExpression().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol365)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol365)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol366)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol366)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol367,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol368,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol369) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol367,GenSymbol368,GenSymbol369)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol370,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol371,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol372) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol370,GenSymbol371,GenSymbol372)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol373,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol374,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol375) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol373,GenSymbol374,GenSymbol375)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol376) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol376)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol377,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol378) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol377,GenSymbol378)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol379) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol379)); }

public BasicMtlASTWithAssociationView.BMTL_Instruction getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression().getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

}
