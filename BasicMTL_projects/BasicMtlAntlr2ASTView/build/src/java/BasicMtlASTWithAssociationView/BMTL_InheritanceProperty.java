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

public class BMTL_InheritanceProperty extends BMTLObject implements BMTL_InheritancePropertyInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_InheritanceProperty oclAsType(Object o)
{     return (BMTL_InheritanceProperty)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_InheritancePropertyInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_InheritanceProperty BMTLRef_BMTL_InheritanceProperty;
private BasicMtlASTWithAssociationView.BMTL_Property BMTLRef_BMTL_Property;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_InheritanceProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("InheritanceProperty");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::InheritanceProperty",this);
theCaller=this;
theLib.recordNewInstance("InheritanceProperty",this);

if (inheritanceMap.containsKey("BasicMtlASTView::InheritanceProperty"))
	BMTLRef_BMTL_InheritanceProperty= (BasicMtlASTView.BMTL_InheritanceProperty)inheritanceMap.get("BasicMtlASTView::InheritanceProperty");
else BMTLRef_BMTL_InheritanceProperty= new BasicMtlASTView.BMTL_InheritanceProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)inheritanceMap.get("BasicMtlASTWithAssociationView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_InheritanceProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_InheritancePropertyInterface o)
{ super("InheritanceProperty");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::InheritanceProperty",this);
theLib.recordNewInstance("InheritanceProperty",theCaller);
if (map.containsKey("BasicMtlASTView::InheritanceProperty"))
	BMTLRef_BMTL_InheritanceProperty= (BasicMtlASTView.BMTL_InheritanceProperty)map.get("BasicMtlASTView::InheritanceProperty");
else BMTLRef_BMTL_InheritanceProperty= new BasicMtlASTView.BMTL_InheritanceProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
if (map.containsKey("BasicMtlASTWithAssociationView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)map.get("BasicMtlASTWithAssociationView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty().delete();
    theLib.removeInstance("InheritanceProperty",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_InheritanceProperty getRef_BMTL_BasicMtlASTWithAssociationView_5fInheritanceProperty()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_InheritanceProperty getRef_BMTL_BasicMtlASTView_5fInheritanceProperty()
{ return this.BMTLRef_BMTL_InheritanceProperty; }

public BasicMtlASTWithAssociationView.BMTL_Property getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty()
{ return this.BMTLRef_BMTL_Property; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"InheritanceProperty"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Property getRef_BMTL_BasicMtlASTView_5fProperty ()
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().getRef_BMTL_BasicMtlASTView_5fProperty ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_value ()
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().get_BMTL_value ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol238)
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().set_BMTL_value (GenSymbol238)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().BMTL_getTheValue ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol239) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().BMTL_setTheValue (GenSymbol239)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fInheritanceProperty().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol240)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_name (GenSymbol240)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_tagType ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_tagType ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tagType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol241)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_tagType (GenSymbol241)); }

}
