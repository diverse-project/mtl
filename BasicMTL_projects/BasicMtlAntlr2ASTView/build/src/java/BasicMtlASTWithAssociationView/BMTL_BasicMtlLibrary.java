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

public class BMTL_BasicMtlLibrary extends BMTLObject implements BMTL_BasicMtlLibraryInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_BasicMtlLibrary oclAsType(Object o)
{     return (BMTL_BasicMtlLibrary)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_BasicMtlLibraryInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_BasicMtlLibrary BMTLRef_BMTL_BasicMtlLibrary;
private BasicMtlASTWithAssociationView.BMTL_Library BMTLRef_BMTL_Library;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_BasicMtlLibrary(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("BasicMtlLibrary");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::BasicMtlLibrary",this);
theCaller=this;
theLib.recordNewInstance("BasicMtlLibrary",this);

if (inheritanceMap.containsKey("BasicMtlASTView::BasicMtlLibrary"))
	BMTLRef_BMTL_BasicMtlLibrary= (BasicMtlASTView.BMTL_BasicMtlLibrary)inheritanceMap.get("BasicMtlASTView::BasicMtlLibrary");
else BMTLRef_BMTL_BasicMtlLibrary= new BasicMtlASTView.BMTL_BasicMtlLibrary((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Library")) 
	BMTLRef_BMTL_Library= (BMTL_Library)inheritanceMap.get("BasicMtlASTWithAssociationView::Library");
else BMTLRef_BMTL_Library= new BMTL_Library(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_BasicMtlLibrary(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_BasicMtlLibraryInterface o)
{ super("BasicMtlLibrary");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::BasicMtlLibrary",this);
theLib.recordNewInstance("BasicMtlLibrary",theCaller);
if (map.containsKey("BasicMtlASTView::BasicMtlLibrary"))
	BMTLRef_BMTL_BasicMtlLibrary= (BasicMtlASTView.BMTL_BasicMtlLibrary)map.get("BasicMtlASTView::BasicMtlLibrary");
else BMTLRef_BMTL_BasicMtlLibrary= new BasicMtlASTView.BMTL_BasicMtlLibrary((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::Library")) 
	BMTLRef_BMTL_Library= (BMTL_Library)map.get("BasicMtlASTWithAssociationView::Library");
else BMTLRef_BMTL_Library= new BMTL_Library(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().delete();
    theLib.removeInstance("BasicMtlLibrary",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_BasicMtlLibrary getRef_BMTL_BasicMtlASTWithAssociationView_5fBasicMtlLibrary()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_BasicMtlLibrary getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary()
{ return this.BMTLRef_BMTL_BasicMtlLibrary; }

public BasicMtlASTWithAssociationView.BMTL_Library getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary()
{ return this.BMTLRef_BMTL_Library; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"BasicMtlLibrary"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().getRef_BMTL_BasicMtlASTView_5fLibrary ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_parameters ()
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().get_BMTL_parameters ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol137)
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().set_BMTL_parameters (GenSymbol137)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters (BasicMtlASTView.BMTL_ModelRefInterface GenSymbol138) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_appendParameters (GenSymbol138)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addClass (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol139) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_addClass (GenSymbol139)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isRefined (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol140,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol141) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_isRefined (GenSymbol140,GenSymbol141)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addLibraries (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol142,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol143) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_addLibraries (GenSymbol142,GenSymbol143)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_findRefinedClasses () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_findRefinedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary().BMTL_transformInheritedLibrary ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol144)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol144)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol145)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol145)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol146)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol146)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol147)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol147)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol148)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol148)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol149) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol149)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol150) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol150)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol151)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol151)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol152,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol153,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol154) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol152,GenSymbol153,GenSymbol154)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol155,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol156,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol157) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol155,GenSymbol156,GenSymbol157)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol158,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol159,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol160) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol158,GenSymbol159,GenSymbol160)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol161) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol161)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol162,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol163) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol162,GenSymbol163)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol164) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol164)); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformAllAssociations (BasicMtlASTWithAssociationView.BMTL_LibraryInterface GenSymbol165) throws Throwable
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().BMTL_transformAllAssociations (GenSymbol165)); }

}
