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

public class BMTL_SelfLiteral extends BMTLObject implements BMTL_SelfLiteralInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_SelfLiteral oclAsType(Object o)
{     return (BMTL_SelfLiteral)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_SelfLiteralInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Literal BMTLRef_BMTL_Literal;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_SelfLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("SelfLiteral");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::SelfLiteral",this);
theCaller=this;
theLib.recordNewInstance("SelfLiteral",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)inheritanceMap.get("BasicMtlASTView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_SelfLiteral(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_SelfLiteralInterface o)
{ super("SelfLiteral");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::SelfLiteral",this);
theLib.recordNewInstance("SelfLiteral",theCaller);
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
    theLib.removeInstance("SelfLiteral",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_SelfLiteral getRef_BMTL_BasicMtlASTView_5fSelfLiteral()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Literal getRef_BMTL_BasicMtlASTView_5fLiteral()
{ return this.BMTLRef_BMTL_Literal; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"SelfLiteral"});
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

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol438)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol438)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol439)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol439)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol440,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol441,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol442) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol440,GenSymbol441,GenSymbol442)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol443,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol444,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol445) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol443,GenSymbol444,GenSymbol445)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol446,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol447,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol448) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol446,GenSymbol447,GenSymbol448)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol449) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol449)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol450,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol451) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol450,GenSymbol451)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol452) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol452)); }

public Object BMTL_toASTJava() throws Throwable {
return new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.SelfLiteral();
}


}
