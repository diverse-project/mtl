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

public class BMTL_Associate extends BMTLObject implements BMTL_AssociateInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Associate oclAsType(Object o)
{     return (BMTL_Associate)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_AssociateInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Associate BMTLRef_BMTL_Associate;
private BasicMtlASTWithAssociationView.BMTL_Instruction BMTLRef_BMTL_Instruction;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Associate(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Associate");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Associate",this);
theCaller=this;
theLib.recordNewInstance("Associate",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Associate"))
	BMTLRef_BMTL_Associate= (BasicMtlASTView.BMTL_Associate)inheritanceMap.get("BasicMtlASTView::Associate");
else BMTLRef_BMTL_Associate= new BasicMtlASTView.BMTL_Associate((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)inheritanceMap.get("BasicMtlASTWithAssociationView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Associate(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_AssociateInterface o)
{ super("Associate");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Associate",this);
theLib.recordNewInstance("Associate",theCaller);
if (map.containsKey("BasicMtlASTView::Associate"))
	BMTLRef_BMTL_Associate= (BasicMtlASTView.BMTL_Associate)map.get("BasicMtlASTView::Associate");
else BMTLRef_BMTL_Associate= new BasicMtlASTView.BMTL_Associate((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)map.get("BasicMtlASTWithAssociationView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fAssociate().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction().delete();
    theLib.removeInstance("Associate",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Associate getRef_BMTL_BasicMtlASTWithAssociationView_5fAssociate()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Associate getRef_BMTL_BasicMtlASTView_5fAssociate()
{ return this.BMTLRef_BMTL_Associate; }

public BasicMtlASTWithAssociationView.BMTL_Instruction getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction()
{ return this.BMTLRef_BMTL_Instruction; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Associate"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction ()
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().getRef_BMTL_BasicMtlASTView_5fInstruction ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_roles ()
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().get_BMTL_roles ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_roles (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol87)
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().set_BMTL_roles (GenSymbol87)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_AssociationName ()
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().get_BMTL_AssociationName ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_AssociationName (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol88)
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().set_BMTL_AssociationName (GenSymbol88)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_isAssociate ()
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().get_BMTL_isAssociate ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isAssociate (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol89)
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().set_BMTL_isAssociate (GenSymbol89)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendRoles (BasicMtlASTView.BMTL_RoleInterface GenSymbol90) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().BMTL_appendRoles (GenSymbol90)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fAssociate().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol91)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol91)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol92)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol92)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol93,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol94,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol95) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol93,GenSymbol94,GenSymbol95)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol96,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol97,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol98) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol96,GenSymbol97,GenSymbol98)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol99,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol100,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol101) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol99,GenSymbol100,GenSymbol101)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol102) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol102)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol103,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol104) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol103,GenSymbol104)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol105) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol105)); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fInstruction().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

}
