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

public class BMTL_Property extends BMTLObject implements BMTL_PropertyInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Property oclAsType(Object o)
{     return (BMTL_Property)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_PropertyInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Property(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("Property");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::Property",this);
theCaller=this;
theLib.recordNewInstance("Property",this);

}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Property(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_PropertyInterface o)
{ super("Property");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::Property",this);
theLib.recordNewInstance("Property",theCaller);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    theLib.removeInstance("Property",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_Property getRef_BMTL_BasicMtlASTView_5fProperty()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Property"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_tagType()
{ return this.BMTL_tagType; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_tagType (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_tagType=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue() throws Throwable {
new BMTLString("Property has no value, use one of its subclass!").BMTL_toErr();
return (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(null);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_newValue) throws Throwable {
new BMTLString("Property has no value, use one of its subclass!").BMTL_toErr();
return BMTLVoid.TheInstance; }


public Object BMTL_toASTJava() throws Throwable {
new BMTLString("toASTJava SHOULD BE DONE ONLY ON SUBCLASSES OF Property").BMTL_toErr();
return (Object)CommonFunctions.toBMTLDataType(null);
}


}
