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

public class BMTL_NullLiteral extends BMTLObject implements BMTL_NullLiteralInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_NullLiteral oclAsType(Object o)
{     return (BMTL_NullLiteral)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_NullLiteralInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_NullLiteral BMTLRef_BMTL_NullLiteral;
private BasicMtlASTWithAssociationView.BMTL_Literal BMTLRef_BMTL_Literal;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_NullLiteral(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("NullLiteral");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::NullLiteral",this);
theCaller=this;
theLib.recordNewInstance("NullLiteral",this);

if (inheritanceMap.containsKey("BasicMtlASTView::NullLiteral"))
	BMTLRef_BMTL_NullLiteral= (BasicMtlASTView.BMTL_NullLiteral)inheritanceMap.get("BasicMtlASTView::NullLiteral");
else BMTLRef_BMTL_NullLiteral= new BasicMtlASTView.BMTL_NullLiteral((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)inheritanceMap.get("BasicMtlASTWithAssociationView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_NullLiteral(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_NullLiteralInterface o)
{ super("NullLiteral");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::NullLiteral",this);
theLib.recordNewInstance("NullLiteral",theCaller);
if (map.containsKey("BasicMtlASTView::NullLiteral"))
	BMTLRef_BMTL_NullLiteral= (BasicMtlASTView.BMTL_NullLiteral)map.get("BasicMtlASTView::NullLiteral");
else BMTLRef_BMTL_NullLiteral= new BasicMtlASTView.BMTL_NullLiteral((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)map.get("BasicMtlASTWithAssociationView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fNullLiteral().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral().delete();
    theLib.removeInstance("NullLiteral",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_NullLiteral getRef_BMTL_BasicMtlASTWithAssociationView_5fNullLiteral()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_NullLiteral getRef_BMTL_BasicMtlASTView_5fNullLiteral()
{ return this.BMTLRef_BMTL_NullLiteral; }

public BasicMtlASTWithAssociationView.BMTL_Literal getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral()
{ return this.BMTLRef_BMTL_Literal; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"NullLiteral"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Literal getRef_BMTL_BasicMtlASTView_5fLiteral ()
{ return (getRef_BMTL_BasicMtlASTView_5fNullLiteral().getRef_BMTL_BasicMtlASTView_5fLiteral ()); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fNullLiteral().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTView_5fLiteral().getRef_BMTL_BasicMtlASTView_5fExpression ()); }

public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fExpression().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol343)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol343)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol344)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol344)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol345,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol346,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol347) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol345,GenSymbol346,GenSymbol347)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol348,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol349,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol350) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol348,GenSymbol349,GenSymbol350)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol351,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol352,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol353) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol351,GenSymbol352,GenSymbol353)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol354) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol354)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol355,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol356) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol355,GenSymbol356)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol357) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol357)); }

public BasicMtlASTWithAssociationView.BMTL_Expression getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral().getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression ()); }

public BasicMtlASTWithAssociationView.BMTL_Instruction getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression().getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

}
