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

public class BMTL_StringProperty extends BMTLObject implements BMTL_StringPropertyInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_StringProperty oclAsType(Object o)
{     return (BMTL_StringProperty)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_StringPropertyInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_StringProperty BMTLRef_BMTL_StringProperty;
private BasicMtlASTWithAssociationView.BMTL_Property BMTLRef_BMTL_Property;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_StringProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("StringProperty");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::StringProperty",this);
theCaller=this;
theLib.recordNewInstance("StringProperty",this);

if (inheritanceMap.containsKey("BasicMtlASTView::StringProperty"))
	BMTLRef_BMTL_StringProperty= (BasicMtlASTView.BMTL_StringProperty)inheritanceMap.get("BasicMtlASTView::StringProperty");
else BMTLRef_BMTL_StringProperty= new BasicMtlASTView.BMTL_StringProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)inheritanceMap.get("BasicMtlASTWithAssociationView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_StringProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_StringPropertyInterface o)
{ super("StringProperty");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::StringProperty",this);
theLib.recordNewInstance("StringProperty",theCaller);
if (map.containsKey("BasicMtlASTView::StringProperty"))
	BMTLRef_BMTL_StringProperty= (BasicMtlASTView.BMTL_StringProperty)map.get("BasicMtlASTView::StringProperty");
else BMTLRef_BMTL_StringProperty= new BasicMtlASTView.BMTL_StringProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
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
    this.getRef_BMTL_BasicMtlASTView_5fStringProperty().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty().delete();
    theLib.removeInstance("StringProperty",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_StringProperty getRef_BMTL_BasicMtlASTWithAssociationView_5fStringProperty()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_StringProperty getRef_BMTL_BasicMtlASTView_5fStringProperty()
{ return this.BMTLRef_BMTL_StringProperty; }

public BasicMtlASTWithAssociationView.BMTL_Property getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty()
{ return this.BMTLRef_BMTL_Property; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"StringProperty"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Property getRef_BMTL_BasicMtlASTView_5fProperty ()
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().getRef_BMTL_BasicMtlASTView_5fProperty ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_value ()
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().get_BMTL_value ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol543)
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().set_BMTL_value (GenSymbol543)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().BMTL_getTheValue ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol544) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().BMTL_setTheValue (GenSymbol544)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fStringProperty().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol545)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_name (GenSymbol545)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_tagType ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_tagType ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tagType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol546)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_tagType (GenSymbol546)); }

}
