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

public class BMTL_StringLiteral extends BMTLObject implements BMTL_StringLiteralInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_StringLiteral oclAsType(Object o)
{     return (BMTL_StringLiteral)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_StringLiteralInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_StringLiteral BMTLRef_BMTL_StringLiteral;
private BasicMtlASTWithAssociationView.BMTL_Literal BMTLRef_BMTL_Literal;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_StringLiteral(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("StringLiteral");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::StringLiteral",this);
theCaller=this;
theLib.recordNewInstance("StringLiteral",this);

if (inheritanceMap.containsKey("BasicMtlASTView::StringLiteral"))
	BMTLRef_BMTL_StringLiteral= (BasicMtlASTView.BMTL_StringLiteral)inheritanceMap.get("BasicMtlASTView::StringLiteral");
else BMTLRef_BMTL_StringLiteral= new BasicMtlASTView.BMTL_StringLiteral((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Literal")) 
	BMTLRef_BMTL_Literal= (BMTL_Literal)inheritanceMap.get("BasicMtlASTWithAssociationView::Literal");
else BMTLRef_BMTL_Literal= new BMTL_Literal(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_StringLiteral(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_StringLiteralInterface o)
{ super("StringLiteral");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::StringLiteral",this);
theLib.recordNewInstance("StringLiteral",theCaller);
if (map.containsKey("BasicMtlASTView::StringLiteral"))
	BMTLRef_BMTL_StringLiteral= (BasicMtlASTView.BMTL_StringLiteral)map.get("BasicMtlASTView::StringLiteral");
else BMTLRef_BMTL_StringLiteral= new BasicMtlASTView.BMTL_StringLiteral((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
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
    this.getRef_BMTL_BasicMtlASTView_5fStringLiteral().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral().delete();
    theLib.removeInstance("StringLiteral",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_StringLiteral getRef_BMTL_BasicMtlASTWithAssociationView_5fStringLiteral()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_StringLiteral getRef_BMTL_BasicMtlASTView_5fStringLiteral()
{ return this.BMTLRef_BMTL_StringLiteral; }

public BasicMtlASTWithAssociationView.BMTL_Literal getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral()
{ return this.BMTLRef_BMTL_Literal; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"StringLiteral"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Literal getRef_BMTL_BasicMtlASTView_5fLiteral ()
{ return (getRef_BMTL_BasicMtlASTView_5fStringLiteral().getRef_BMTL_BasicMtlASTView_5fLiteral ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_value ()
{ return (getRef_BMTL_BasicMtlASTView_5fStringLiteral().get_BMTL_value ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol527)
{ return (getRef_BMTL_BasicMtlASTView_5fStringLiteral().set_BMTL_value (GenSymbol527)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fStringLiteral().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_Expression getRef_BMTL_BasicMtlASTView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTView_5fLiteral().getRef_BMTL_BasicMtlASTView_5fExpression ()); }

public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fExpression().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol528)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol528)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol529)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol529)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol530,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol531,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol532) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol530,GenSymbol531,GenSymbol532)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol533,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol534,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol535) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol533,GenSymbol534,GenSymbol535)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol536,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol537,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol538) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol536,GenSymbol537,GenSymbol538)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol539) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol539)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol540,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol541) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol540,GenSymbol541)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol542) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol542)); }

public BasicMtlASTWithAssociationView.BMTL_Expression getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLiteral().getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression ()); }

public BasicMtlASTWithAssociationView.BMTL_Instruction getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fExpression().getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction ()); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

}
