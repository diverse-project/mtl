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

public class BMTL_BMTLTypeProperty extends BMTLObject implements BMTL_BMTLTypePropertyInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_BMTLTypeProperty oclAsType(Object o)
{     return (BMTL_BMTLTypeProperty)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_BMTLTypePropertyInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_BMTLTypeProperty BMTLRef_BMTL_BMTLTypeProperty;
private BasicMtlASTWithAssociationView.BMTL_Property BMTLRef_BMTL_Property;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_BMTLTypeProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("BMTLTypeProperty");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::BMTLTypeProperty",this);
theCaller=this;
theLib.recordNewInstance("BMTLTypeProperty",this);

if (inheritanceMap.containsKey("BasicMtlASTView::BMTLTypeProperty"))
	BMTLRef_BMTL_BMTLTypeProperty= (BasicMtlASTView.BMTL_BMTLTypeProperty)inheritanceMap.get("BasicMtlASTView::BMTLTypeProperty");
else BMTLRef_BMTL_BMTLTypeProperty= new BasicMtlASTView.BMTL_BMTLTypeProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)inheritanceMap.get("BasicMtlASTWithAssociationView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_BMTLTypeProperty(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_BMTLTypePropertyInterface o)
{ super("BMTLTypeProperty");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::BMTLTypeProperty",this);
theLib.recordNewInstance("BMTLTypeProperty",theCaller);
if (map.containsKey("BasicMtlASTView::BMTLTypeProperty"))
	BMTLRef_BMTL_BMTLTypeProperty= (BasicMtlASTView.BMTL_BMTLTypeProperty)map.get("BasicMtlASTView::BMTLTypeProperty");
else BMTLRef_BMTL_BMTLTypeProperty= new BasicMtlASTView.BMTL_BMTLTypeProperty((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
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
    this.getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty().delete();
    theLib.removeInstance("BMTLTypeProperty",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_BMTLTypeProperty getRef_BMTL_BasicMtlASTWithAssociationView_5fBMTLTypeProperty()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_BMTLTypeProperty getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty()
{ return this.BMTLRef_BMTL_BMTLTypeProperty; }

public BasicMtlASTWithAssociationView.BMTL_Property getRef_BMTL_BasicMtlASTWithAssociationView_5fProperty()
{ return this.BMTLRef_BMTL_Property; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"BMTLTypeProperty"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_Property getRef_BMTL_BasicMtlASTView_5fProperty ()
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().getRef_BMTL_BasicMtlASTView_5fProperty ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_value ()
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().get_BMTL_value ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol166)
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().set_BMTL_value (GenSymbol166)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().BMTL_getTheValue ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol167) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().BMTL_setTheValue (GenSymbol167)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fBMTLTypeProperty().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol168)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_name (GenSymbol168)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_tagType ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_tagType ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tagType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol169)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_tagType (GenSymbol169)); }

}
