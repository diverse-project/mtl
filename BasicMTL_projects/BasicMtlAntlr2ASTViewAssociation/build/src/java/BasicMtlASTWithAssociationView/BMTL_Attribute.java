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

public class BMTL_Attribute extends BMTLObject implements BMTL_AttributeInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Attribute oclAsType(Object o)
{     return (BMTL_Attribute)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_AttributeInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Attribute BMTLRef_BMTL_Attribute;
private BasicMtlASTWithAssociationView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Attribute(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Attribute");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Attribute",this);
theCaller=this;
theLib.recordNewInstance("Attribute",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Attribute"))
	BMTLRef_BMTL_Attribute= (BasicMtlASTView.BMTL_Attribute)inheritanceMap.get("BasicMtlASTView::Attribute");
else BMTLRef_BMTL_Attribute= new BasicMtlASTView.BMTL_Attribute((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Attribute(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_AttributeInterface o)
{ super("Attribute");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Attribute",this);
theLib.recordNewInstance("Attribute",theCaller);
if (map.containsKey("BasicMtlASTView::Attribute"))
	BMTLRef_BMTL_Attribute= (BasicMtlASTView.BMTL_Attribute)map.get("BasicMtlASTView::Attribute");
else BMTLRef_BMTL_Attribute= new BasicMtlASTView.BMTL_Attribute((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)map.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fAttribute().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().delete();
    theLib.removeInstance("Attribute",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Attribute getRef_BMTL_BasicMtlASTWithAssociationView_5fAttribute()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Attribute getRef_BMTL_BasicMtlASTView_5fAttribute()
{ return this.BMTLRef_BMTL_Attribute; }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Attribute"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol120)
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().set_BMTL_name (GenSymbol120)); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleGetter ()
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().get_BMTL_possibleGetter ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleGetter (BasicMtlASTView.BMTL_OperationInterface GenSymbol121)
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().set_BMTL_possibleGetter (GenSymbol121)); }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleSetter ()
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().get_BMTL_possibleSetter ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleSetter (BasicMtlASTView.BMTL_OperationInterface GenSymbol122)
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().set_BMTL_possibleSetter (GenSymbol122)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fAttribute().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol123)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol123)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol124,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol125,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol126) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol124,GenSymbol125,GenSymbol126)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol127,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol128,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol129) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol127,GenSymbol128,GenSymbol129)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol130,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol131,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol132) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol130,GenSymbol131,GenSymbol132)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol133) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol133)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol134,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol135) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol134,GenSymbol135)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol136) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol136)); }

}
