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

public class BMTL_StringProperty extends BMTLObject implements BMTL_StringPropertyInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_StringProperty oclAsType(Object o)
{     return (BMTL_StringProperty)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_StringPropertyInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Property BMTLRef_BMTL_Property;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_value;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_StringProperty(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("StringProperty");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::StringProperty",this);
theCaller=this;
theLib.recordNewInstance("StringProperty",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)inheritanceMap.get("BasicMtlASTView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_StringProperty(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_StringPropertyInterface o)
{ super("StringProperty");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::StringProperty",this);
theLib.recordNewInstance("StringProperty",theCaller);
if (map.containsKey("BasicMtlASTView::Property")) 
	BMTLRef_BMTL_Property= (BMTL_Property)map.get("BasicMtlASTView::Property");
else BMTLRef_BMTL_Property= new BMTL_Property(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fProperty().delete();
    theLib.removeInstance("StringProperty",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_StringProperty getRef_BMTL_BasicMtlASTView_5fStringProperty()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Property getRef_BMTL_BasicMtlASTView_5fProperty()
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
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol468)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_name (GenSymbol468)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_tagType ()
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().get_BMTL_tagType ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tagType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol469)
{ return (getRef_BMTL_BasicMtlASTView_5fProperty().set_BMTL_tagType (GenSymbol469)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_value()
{ return this.BMTL_value; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_value=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue() throws Throwable {
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(get_BMTL_value())));
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_newValue) throws Throwable {
set_BMTL_value(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface)CommonFunctions.toBMTLDataType(BMTL_newValue)));
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
return new org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property(this.get_BMTL_name().getTheString(),BMTL_value.getTheString(),this.get_BMTL_tagType().getTheString());
}


}
