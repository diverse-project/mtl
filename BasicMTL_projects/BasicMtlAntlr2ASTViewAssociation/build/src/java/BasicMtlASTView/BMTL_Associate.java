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

public class BMTL_Associate extends BMTLObject implements BMTL_AssociateInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Associate oclAsType(Object o)
{     return (BMTL_Associate)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_AssociateInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Instruction BMTLRef_BMTL_Instruction;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_roles;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_AssociationName;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isAssociate;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Associate(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("Associate");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::Associate",this);
theCaller=this;
theLib.recordNewInstance("Associate",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Instruction")) 
	BMTLRef_BMTL_Instruction= (BMTL_Instruction)inheritanceMap.get("BasicMtlASTView::Instruction");
else BMTLRef_BMTL_Instruction= new BMTL_Instruction(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Associate(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_AssociateInterface o)
{ super("Associate");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::Associate",this);
theLib.recordNewInstance("Associate",theCaller);
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
    theLib.removeInstance("Associate",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_Associate getRef_BMTL_BasicMtlASTView_5fAssociate()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Instruction getRef_BMTL_BasicMtlASTView_5fInstruction()
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
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_containerOp ()
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().get_BMTL_containerOp ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_containerOp (BasicMtlASTView.BMTL_OperationInterface GenSymbol1)
{ return (getRef_BMTL_BasicMtlASTView_5fInstruction().set_BMTL_containerOp (GenSymbol1)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol2)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol2)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol3,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol4,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol5) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol3,GenSymbol4,GenSymbol5)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol6,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol7,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol8) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol6,GenSymbol7,GenSymbol8)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol9,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol10,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol11) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol9,GenSymbol10,GenSymbol11)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol12) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol12)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol13,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol14) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol13,GenSymbol14)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol15) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol15)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_roles()
{ return this.BMTL_roles; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_roles (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_roles=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_AssociationName()
{ return this.BMTL_AssociationName; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_AssociationName (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_AssociationName=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_isAssociate()
{ return this.BMTL_isAssociate; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isAssociate (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value)
{ this.BMTL_isAssociate=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendRoles(BasicMtlASTView.BMTL_RoleInterface BMTL_aRole) throws Throwable {
BooleanValue GenSymbol16 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_roles());
if (GenSymbol16.getTheBoolean()) {
BMTL_roles=new BMTLSet();
}
set_BMTL_roles(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_roles().BMTL_including(BMTL_aRole))));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
BasicMtlASTView.BMTL_RoleInterface BMTL_aRole=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_theRoles=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Associate theAssociate=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Associate(BMTL_isAssociate.getTheBoolean());
BMTL_theRoles=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_roles().BMTL_getNewIterator();
BMTL_theRoles.BMTL_start();
BooleanValue GenSymbol17;
do {GenSymbol17 = (BooleanValue)BMTL_theRoles.BMTL_isOn();
if (GenSymbol17.getTheBoolean()) {
BMTL_aRole=(BasicMtlASTView.BMTL_RoleInterface)((BasicMtlASTView.BMTL_RoleInterface)CommonFunctions.toBMTLDataType(BMTL_theRoles.BMTL_item()));
theAssociate.appendRoles((org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role)BMTL_aRole.BMTL_toASTJava());
BMTL_theRoles.BMTL_next();
}

} while (GenSymbol17.getTheBoolean());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol18;
do {GenSymbol18 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol18.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theAssociate.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol18.getTheBoolean());
return theAssociate;
}


}
