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

public class BMTL_Library extends BMTLObject implements BMTL_LibraryInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Library oclAsType(Object o)
{     return (BMTL_Library)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_LibraryInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTWithAssociationView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
private BasicMtlASTView.BMTL_Library BMTLRef_BMTL_Library;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Library(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Library");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Library",this);
theCaller=this;
theLib.recordNewInstance("Library",this);

if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTView::Library"))
	BMTLRef_BMTL_Library= (BasicMtlASTView.BMTL_Library)inheritanceMap.get("BasicMtlASTView::Library");
else BMTLRef_BMTL_Library= new BasicMtlASTView.BMTL_Library((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Library(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_LibraryInterface o)
{ super("Library");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Library",this);
theLib.recordNewInstance("Library",theCaller);
if (map.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)map.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,map,o);
if (map.containsKey("BasicMtlASTView::Library"))
	BMTLRef_BMTL_Library= (BasicMtlASTView.BMTL_Library)map.get("BasicMtlASTView::Library");
else BMTLRef_BMTL_Library= new BasicMtlASTView.BMTL_Library((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().delete();
    this.getRef_BMTL_BasicMtlASTView_5fLibrary().delete();
    theLib.removeInstance("Library",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Library getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary()
{ return this.BMTLRef_BMTL_Library; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Library"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol65)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol65)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol66,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol67,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol68) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol66,GenSymbol67,GenSymbol68)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol69,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol70,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol71) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol69,GenSymbol70,GenSymbol71)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol72,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol73,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol74) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol72,GenSymbol73,GenSymbol74)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol75) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol75)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol76,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol77) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol76,GenSymbol77)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol78) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol78)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol79)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol79)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol80)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol80)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol81)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol81)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol82)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol82)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol83)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol83)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol84) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol84)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol85) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol85)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_transformInheritedLibrary ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformAllAssociations(BasicMtlASTWithAssociationView.BMTL_LibraryInterface BMTL_templatesLib) throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_assocInstances=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_instances=null;
BasicMtlASTView.BMTL_UserClassInterface BMTL_associationClass=null;
BMTL_assocInstances=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)new BMTLOclType(this.getLibrary(), new String [] {"BasicMtlASTWithAssociationView", "Association"}).BMTL_allInstances();
BMTL_instances=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)BMTL_assocInstances.BMTL_getNewIterator();
BMTL_instances.BMTL_start();
BooleanValue GenSymbol86;
do {GenSymbol86 = (BooleanValue)BMTL_instances.BMTL_isOn();
if (GenSymbol86.getTheBoolean()) {
BMTL_associationClass=(BasicMtlASTView.BMTL_UserClassInterface)((BasicMtlASTWithAssociationView.BMTL_AssociationInterface)CommonFunctions.toBMTLDataType(BMTL_instances.BMTL_item())).getRef_BMTL_BasicMtlASTWithAssociationView_5fAssociation().BMTL_transformAssociation(BMTL_templatesLib,theCaller.get_BMTL_definedClasses());
theCaller.set_BMTL_definedClasses(theCaller.get_BMTL_definedClasses().BMTL_including(BMTL_associationClass));
BMTL_instances.BMTL_next();
}

} while (GenSymbol86.getTheBoolean());
return BMTLVoid.TheInstance; }


}
