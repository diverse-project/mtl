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

public class BMTL_Attribute extends BMTLObject implements BMTL_AttributeInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Attribute oclAsType(Object o)
{     return (BMTL_Attribute)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theLib;
private BMTL_AttributeInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name;

BasicMtlASTView.BMTL_OperationInterface BMTL_possibleGetter;

BasicMtlASTView.BMTL_OperationInterface BMTL_possibleSetter;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Attribute(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef)
{ super("Attribute");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTView::Attribute",this);
theCaller=this;
theLib.recordNewInstance("Attribute",this);

if (inheritanceMap.containsKey("BasicMtlASTView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Attribute(BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface libRef,java.util.Hashtable map,BMTL_AttributeInterface o)
{ super("Attribute");
theLib = (BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTView::Attribute",this);
theLib.recordNewInstance("Attribute",theCaller);
if (map.containsKey("BasicMtlASTView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)map.get("BasicMtlASTView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,map,o);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    this.getRef_BMTL_BasicMtlASTView_5fASTNode().delete();
    theLib.removeInstance("Attribute",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTView.BMTL_Attribute getRef_BMTL_BasicMtlASTView_5fAttribute()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode()
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
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol28)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol28)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol29,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol30,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol31) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol29,GenSymbol30,GenSymbol31)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol32,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol33,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol34) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol32,GenSymbol33,GenSymbol34)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol35,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol36,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol37) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol35,GenSymbol36,GenSymbol37)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol38) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol38)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol39,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol40) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol39,GenSymbol40)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol41) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol41)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name()
{ return this.BMTL_name; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value)
{ this.BMTL_name=value;
return BMTLVoid.TheInstance; }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleGetter()
{ return this.BMTL_possibleGetter; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleGetter (BasicMtlASTView.BMTL_OperationInterface value)
{ this.BMTL_possibleGetter=value;
return BMTLVoid.TheInstance; }

public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleSetter()
{ return this.BMTL_possibleSetter; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleSetter (BasicMtlASTView.BMTL_OperationInterface value)
{ this.BMTL_possibleSetter=value;
return BMTLVoid.TheInstance; }

public Object BMTL_toASTJava() throws Throwable {
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface BMTL_properties=null;
BasicMtlASTView.BMTL_PropertyInterface BMTL_aProperty=null;
org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute theAttribute=new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute(this.BMTL_name.getTheString());
BMTL_properties=(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)((BasicMtlASTView.BMTL_ASTNodeInterface)CommonFunctions.toBMTLDataType(theCaller)).getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration().BMTL_getNewIterator();
BMTL_properties.BMTL_start();
BooleanValue GenSymbol42;
do {GenSymbol42 = (BooleanValue)BMTL_properties.BMTL_isOn();
if (GenSymbol42.getTheBoolean()) {
BMTL_aProperty=(BasicMtlASTView.BMTL_PropertyInterface)((BasicMtlASTView.BMTL_PropertyInterface)CommonFunctions.toBMTLDataType(BMTL_properties.BMTL_item()));
theAttribute.appendDecoration((org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)BMTL_aProperty.BMTL_toASTJava());
BMTL_properties.BMTL_next();
}

} while (GenSymbol42.getTheBoolean());
return theAttribute;
}


}
