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

public class BMTL_ASTNode extends BMTLObject implements BMTL_ASTNodeInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_ASTNode oclAsType(Object o)
{     return (BMTL_ASTNode)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_ASTNodeInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_decoration;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_ASTNode(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("ASTNode");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::ASTNode",this);
theCaller=this;
theLib.recordNewInstance("ASTNode",this);

}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_ASTNode(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_ASTNodeInterface o)
{ super("ASTNode");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::ASTNode",this);
theLib.recordNewInstance("ASTNode",theCaller);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    theLib.removeInstance("ASTNode",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"ASTNode"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration()
{ return this.BMTL_decoration; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value)
{ this.BMTL_decoration=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable {
BasicMtlASTView.BMTL_BMTLTypePropertyInterface BMTL_theProperty=null;
BMTL_theProperty=(BasicMtlASTView.BMTL_BMTLTypePropertyInterface)null;
BooleanValue GenSymbol19 = (BooleanValue)BMTL_tagType.BMTL__3d(new BMTLString("TypeTag")).BMTL_or(BMTL_tagType.BMTL__3d(new BMTLString("Tag")));
if (GenSymbol19.getTheBoolean()) {
BMTL_theProperty=(BasicMtlASTView.BMTL_BMTLTypePropertyInterface)((BasicMtlASTView.BMTL_BMTLTypePropertyInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTView", "BMTLTypeProperty"})).instanciate()));
BMTL_theProperty.set_BMTL_name(BMTL_key);
BMTL_theProperty.set_BMTL_value(BMTL_value);
BMTL_theProperty.set_BMTL_tagType(BMTL_tagType);
BooleanValue GenSymbol20 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_decoration());
if (GenSymbol20.getTheBoolean()) {
BMTL_decoration=new BMTLSet();
}
set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_decoration().BMTL_including(((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_theProperty))))));
} else {
new BMTLString("Bad tagType to create new BMTLTypeProperty.").BMTL_toErr();
}
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable {
BasicMtlASTView.BMTL_InheritancePropertyInterface BMTL_theProperty=null;
BMTL_theProperty=(BasicMtlASTView.BMTL_InheritancePropertyInterface)null;
BooleanValue GenSymbol21 = (BooleanValue)BMTL_tagType.BMTL__3d(new BMTLString("InheritanceTag"));
if (GenSymbol21.getTheBoolean()) {
BMTL_theProperty=(BasicMtlASTView.BMTL_InheritancePropertyInterface)((BasicMtlASTView.BMTL_InheritancePropertyInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTView", "InheritanceProperty"})).instanciate()));
BMTL_theProperty.set_BMTL_name(BMTL_key);
BMTL_theProperty.set_BMTL_value(BMTL_value);
BMTL_theProperty.set_BMTL_tagType(BMTL_tagType);
BooleanValue GenSymbol22 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_decoration());
if (GenSymbol22.getTheBoolean()) {
BMTL_decoration=new BMTLSet();
}
set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_decoration().BMTL_including(((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_theProperty))))));
} else {
new BMTLString("Bad tagType to create new InheritanceProperty.").BMTL_toErr();
}
return BMTLVoid.TheInstance; }


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable {
BasicMtlASTView.BMTL_StringPropertyInterface BMTL_theProperty=null;
BMTL_theProperty=(BasicMtlASTView.BMTL_StringPropertyInterface)null;
BooleanValue GenSymbol23 = (BooleanValue)BMTL_tagType.BMTL__3d(new BMTLString("StringTag"));
if (GenSymbol23.getTheBoolean()) {
BMTL_theProperty=(BasicMtlASTView.BMTL_StringPropertyInterface)((BasicMtlASTView.BMTL_StringPropertyInterface)CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {"BasicMtlASTView", "StringProperty"})).instanciate()));
BMTL_theProperty.set_BMTL_name(BMTL_key);
BMTL_theProperty.set_BMTL_value(BMTL_value);
BMTL_theProperty.set_BMTL_tagType(BMTL_tagType);
BooleanValue GenSymbol24 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_decoration());
if (GenSymbol24.getTheBoolean()) {
BMTL_decoration=new BMTLSet();
}
set_BMTL_decoration(((org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface)CommonFunctions.toBMTLDataType(get_BMTL_decoration().BMTL_including(((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_theProperty))))));
} else {
new BMTLString("Bad tagType to create new StringProperty.").BMTL_toErr();
}
return BMTLVoid.TheInstance; }


public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key) throws Throwable {
BasicMtlASTView.BMTL_PropertyInterface BMTL_property=null;
BMTL_property=(BasicMtlASTView.BMTL_PropertyInterface)theCaller.BMTL_findProperty(BMTL_key);
return (BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_property);
}


public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_value) throws Throwable {
BasicMtlASTView.BMTL_PropertyInterface BMTL_property=null;
BMTL_property=(BasicMtlASTView.BMTL_PropertyInterface)theCaller.BMTL_findProperty(BMTL_key);
BMTL_property.BMTL_setTheValue(BMTL_value);
return BMTLVoid.TheInstance; }


public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name) throws Throwable {
BasicMtlASTView.BMTL_PropertyInterface BMTL_current=null;
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BooleanValue GenSymbol25 = (BooleanValue)theCaller.BMTL_isNull(get_BMTL_decoration());
if (GenSymbol25.getTheBoolean()) {
return (BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(null);
}
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol26;
do {GenSymbol26 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol26.getTheBoolean()) {
BMTL_current=(BasicMtlASTView.BMTL_PropertyInterface)BMTL_properties.BMTL_item();
BooleanValue GenSymbol27 = (BooleanValue)BMTL_current.get_BMTL_name().BMTL__3d(BMTL_name);
if (GenSymbol27.getTheBoolean()) {
return (BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_current);
}
BMTL_properties.BMTL_next();
}

} while (GenSymbol26.getTheBoolean());
return (BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(null);
}


}
