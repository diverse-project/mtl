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

public class BMTL_RepositoryRef extends BMTLObject implements BMTL_RepositoryRefInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_RepositoryRef oclAsType(Object o)
{     return (BMTL_RepositoryRef)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_RepositoryRefInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_ModelRef BMTLRef_BMTL_ModelRef;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_RepositoryRef(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("RepositoryRef");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::RepositoryRef",this);
theCaller=this;
theLib.recordNewInstance("RepositoryRef",this);

if (inheritanceMap.containsKey("BasicMtlASTView::ModelRef")) 
	BMTLRef_BMTL_ModelRef= (BMTL_ModelRef)inheritanceMap.get("BasicMtlASTView::ModelRef");
else BMTLRef_BMTL_ModelRef= new BMTL_ModelRef(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_RepositoryRef(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_RepositoryRefInterface o)
{ super("RepositoryRef");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::RepositoryRef",this);
theLib.recordNewInstance("RepositoryRef",theCaller);
if (map.containsKey("BasicMtlASTView::ModelRef")) 
	BMTLRef_BMTL_ModelRef= (BMTL_ModelRef)map.get("BasicMtlASTView::ModelRef");
else BMTLRef_BMTL_ModelRef= new BMTL_ModelRef(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fModelRef().delete();
    theLib.removeInstance("RepositoryRef",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_RepositoryRef getRef_BMTL_BasicMtlASTView_5fRepositoryRef()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_ModelRef getRef_BMTL_BasicMtlASTView_5fModelRef()
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
public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary ()
{ return (getRef_BMTL_BasicMtlASTView_5fModelRef().getRef_BMTL_BasicMtlASTView_5fLibrary ()); }

public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedClasses ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol384)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedClasses (GenSymbol384)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_definedOperations ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol385)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_definedOperations (GenSymbol385)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol386)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_name (GenSymbol386)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasInheritance ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol387)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasInheritance (GenSymbol387)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation ()
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().get_BMTL_hasAssociation ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol388)
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().set_BMTL_hasAssociation (GenSymbol388)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations (BasicMtlASTView.BMTL_OperationInterface GenSymbol389) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedOperations (GenSymbol389)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses (BasicMtlASTView.BMTL_UserClassInterface GenSymbol390) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_appendDefinedClasses (GenSymbol390)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fLibrary().BMTL_transformInheritedLibrary ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol391)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol391)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol392,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol393,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol394) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol392,GenSymbol393,GenSymbol394)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol395,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol396,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol397) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol395,GenSymbol396,GenSymbol397)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol398,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol399,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol400) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol398,GenSymbol399,GenSymbol400)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol401) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol401)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol402,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol403) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol402,GenSymbol403)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol404) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol404)); }

public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.RepositoryRef theRepositoryRef=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.RepositoryRef(this.get_BMTL_name().getTheString());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol405;
do {GenSymbol405 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol405.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theRepositoryRef.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol405.getTheBoolean());
return theRepositoryRef;
}


}
