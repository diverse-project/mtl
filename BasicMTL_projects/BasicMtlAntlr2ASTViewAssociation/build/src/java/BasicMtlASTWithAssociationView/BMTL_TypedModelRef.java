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

public class BMTL_TypedModelRef extends BMTLObject implements BMTL_TypedModelRefInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_TypedModelRef oclAsType(Object o)
{     return (BMTL_TypedModelRef)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_TypedModelRefInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_TypedModelRef BMTLRef_BMTL_TypedModelRef;
private BasicMtlASTWithAssociationView.BMTL_ModelRef BMTLRef_BMTL_ModelRef;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_TypedModelRef(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("TypedModelRef");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::TypedModelRef",this);
theCaller=this;
theLib.recordNewInstance("TypedModelRef",this);

if (inheritanceMap.containsKey("BasicMtlASTView::TypedModelRef"))
	BMTLRef_BMTL_TypedModelRef= (BasicMtlASTView.BMTL_TypedModelRef)inheritanceMap.get("BasicMtlASTView::TypedModelRef");
else BMTLRef_BMTL_TypedModelRef= new BasicMtlASTView.BMTL_TypedModelRef((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ModelRef")) 
	BMTLRef_BMTL_ModelRef= (BMTL_ModelRef)inheritanceMap.get("BasicMtlASTWithAssociationView::ModelRef");
else BMTLRef_BMTL_ModelRef= new BMTL_ModelRef(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_TypedModelRef(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_TypedModelRefInterface o)
{ super("TypedModelRef");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::TypedModelRef",this);
theLib.recordNewInstance("TypedModelRef",theCaller);
if (map.containsKey("BasicMtlASTView::TypedModelRef"))
	BMTLRef_BMTL_TypedModelRef= (BasicMtlASTView.BMTL_TypedModelRef)map.get("BasicMtlASTView::TypedModelRef");
else BMTLRef_BMTL_TypedModelRef= new BasicMtlASTView.BMTL_TypedModelRef((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
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
    this.getRef_BMTL_BasicMtlASTView_5fTypedModelRef().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef().delete();
    theLib.removeInstance("TypedModelRef",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_TypedModelRef getRef_BMTL_BasicMtlASTWithAssociationView_5fTypedModelRef()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_TypedModelRef getRef_BMTL_BasicMtlASTView_5fTypedModelRef()
{ return this.BMTLRef_BMTL_TypedModelRef; }

public BasicMtlASTWithAssociationView.BMTL_ModelRef getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef()
{ return this.BMTLRef_BMTL_ModelRef; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"TypedModelRef"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ModelRef getRef_BMTL_BasicMtlASTView_5fModelRef ()
{ return (getRef_BMTL_BasicMtlASTView_5fTypedModelRef().getRef_BMTL_BasicMtlASTView_5fModelRef ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_typeName ()
{ return (getRef_BMTL_BasicMtlASTView_5fTypedModelRef().get_BMTL_typeName ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_typeName (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol584)
{ return (getRef_BMTL_BasicMtlASTView_5fTypedModelRef().set_BMTL_typeName (GenSymbol584)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fTypedModelRef().BMTL_toASTJava ()); }

public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTView_5fModelRef().getRef_BMTL_BasicMtlASTView_5fLibrary ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol585)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol585)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol586)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol586)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol587)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol587)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol588)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol588)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol589)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol589)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol590) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol590)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol591) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol591)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_transformInheritedLibrary ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol592)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol592)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol593,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol594,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol595) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol593,GenSymbol594,GenSymbol595)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol596,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol597,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol598) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol596,GenSymbol597,GenSymbol598)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol599,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol600,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol601) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol599,GenSymbol600,GenSymbol601)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol602) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol602)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol603,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol604) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol603,GenSymbol604)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol605) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol605)); }

public BasicMtlASTWithAssociationView.BMTL_Library getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fModelRef().getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary ()); }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformAllAssociations (BasicMtlASTWithAssociationView.BMTL_LibraryInterface GenSymbol606) throws Throwable
{ return (getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary().BMTL_transformAllAssociations (GenSymbol606)); }

}
