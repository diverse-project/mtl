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

public class BMTL_RealLiteral extends BMTLObject implements BMTL_RealLiteralInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_RealLiteral oclAsType(Object o)
{     return (BMTL_RealLiteral)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_RealLiteralInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Literal BMTLRef_BMTL_Literal;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface BMTL_value;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_RealLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("RealLiteral");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::RealLiteral",this);
theCaller=this;
theLib.recordNewInstance("RealLiteral",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)inheritanceMap.get("BasicMtlASTView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_RealLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_RealLiteralInterface o)
{ super("RealLiteral");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::RealLiteral",this);
theLib.recordNewInstance("RealLiteral",theCaller);
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
    theLib.removeInstance("RealLiteral",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_RealLiteral getRef_BMTL_BasicMtlASTView_5fRealLiteral()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Literal getRef_BMTL_BasicMtlASTView_5fLiteral()
{ return this.BMTLRef_BMTL_Literal; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"RealLiteral"});
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

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol369)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol369)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol370)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol370)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol371,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol372,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol373) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol371,GenSymbol372,GenSymbol373)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol374,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol375,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol376) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol374,GenSymbol375,GenSymbol376)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol377,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol378,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol379) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol377,GenSymbol378,GenSymbol379)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol380) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol380)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol381,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol382) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol381,GenSymbol382)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol383) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol383)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface get_BMTL_value()
{ return this.BMTL_value; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRealInterface value)
{ this.BMTL_value=value;
return BMTLVoid.TheInstance; }

public Object BMTL_toASTJava() throws Throwable {
return new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.RealLiteral(this.BMTL_value.getTheReal());
}


}
