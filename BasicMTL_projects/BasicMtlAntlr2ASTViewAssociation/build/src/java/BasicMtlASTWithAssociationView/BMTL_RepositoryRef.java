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

public class BMTL_RepositoryRef extends BMTLObject implements BMTL_RepositoryRefInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_RepositoryRef oclAsType(Object o)
{     return (BMTL_RepositoryRef)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_RepositoryRefInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_RepositoryRef BMTLRef_BMTL_RepositoryRef;
private BasicMtlASTWithAssociationView.BMTL_ModelRef BMTLRef_BMTL_ModelRef;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_RepositoryRef(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("RepositoryRef");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::RepositoryRef",this);
theCaller=this;
theLib.recordNewInstance("RepositoryRef",this);

if (inheritanceMap.containsKey("BasicMtlASTView::RepositoryRef"))
	BMTLRef_BMTL_RepositoryRef= (BasicMtlASTView.BMTL_RepositoryRef)inheritanceMap.get("BasicMtlASTView::RepositoryRef");
else BMTLRef_BMTL_RepositoryRef= new BasicMtlASTView.BMTL_RepositoryRef((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ModelRef")) 
	BMTLRef_BMTL_ModelRef= (BMTL_ModelRef)inheritanceMap.get("BasicMtlASTWithAssociationView::ModelRef");
else BMTLRef_BMTL_ModelRef= new BMTL_ModelRef(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_RepositoryRef(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_RepositoryRefInterface o)
{ super("RepositoryRef");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::RepositoryRef",this);
theLib.recordNewInstance("RepositoryRef",theCaller);
if (map.containsKey("BasicMtlASTView::RepositoryRef"))
	BMTLRef_BMTL_RepositoryRef= (BasicMtlASTView.BMTL_RepositoryRef)map.get("BasicMtlASTView::RepositoryRef");
else BMTLRef_BMTL_RepositoryRef= new BasicMtlASTView.BMTL_RepositoryRef((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::ModelRef")) 
	BMTLRef_BMTL_ModelRef= (BMTL_ModelRef)map.get("BasicMtlASTWithAssociationView::ModelRef");
else BMTLRef_BMTL_ModelRef= new BMTL_ModelRef(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fRepositoryRef().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef().delete();
    theLib.removeInstance("RepositoryRef",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_RepositoryRef getRef_BMTL_BasicMtlASTWithAssociationView_5fRepositoryRef()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_RepositoryRef getRef_BMTL_BasicMtlASTView_5fRepositoryRef()
{ return this.BMTLRef_BMTL_RepositoryRef; }

public BasicMtlASTWithAssociationView.BMTL_ModelRef getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef()
{ return this.BMTLRef_BMTL_ModelRef; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"RepositoryRef"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ModelRef getRef_BMTL_BasicMtlASTView_5fModelRef ()
{ return (getRef_BMTL_BasicMtlASTView_5fRepositoryRef().getRef_BMTL_BasicMtlASTView_5fModelRef ()); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fRepositoryRef().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTView_5fModelRef().getRef_BMTL_BasicMtlASTView_5fLibrary ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol458)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol458)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol459)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol459)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol460)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol460)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol461)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol461)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol462)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol462)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol463) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol463)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol464) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol464)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_transformInheritedLibrary ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol465)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol465)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol466,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol467,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol468) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol466,GenSymbol467,GenSymbol468)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol469,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol470,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol471) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol469,GenSymbol470,GenSymbol471)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol472,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol473,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol474) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol472,GenSymbol473,GenSymbol474)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol475) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol475)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol476,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol477) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol476,GenSymbol477)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol478) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol478)); }

public BasicMtlASTWithAssociationView.BMTL_Library getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef().getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary ()); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformAllAssociations (BasicMtlASTWithAssociationView.BMTL_LibraryInterface GenSymbol479) throws Throwable
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().BMTL_transformAllAssociations (GenSymbol479)); }

}
