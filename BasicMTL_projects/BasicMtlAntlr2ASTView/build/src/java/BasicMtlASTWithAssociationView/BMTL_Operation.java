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

public class BMTL_Operation extends BMTLObject implements BMTL_OperationInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Operation oclAsType(Object o)
{     return (BMTL_Operation)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_OperationInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
private BasicMtlASTView.BMTL_Operation BMTLRef_BMTL_Operation;
private BasicMtlASTWithAssociationView.BMTL_ASTNode BMTLRef_BMTL_ASTNode;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Operation(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Operation");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Operation",this);
theCaller=this;
theLib.recordNewInstance("Operation",this);

if (inheritanceMap.containsKey("BasicMtlASTView::Operation"))
	BMTLRef_BMTL_Operation= (BasicMtlASTView.BMTL_Operation)inheritanceMap.get("BasicMtlASTView::Operation");
else BMTLRef_BMTL_Operation= new BasicMtlASTView.BMTL_Operation((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),inheritanceMap,this);
if (inheritanceMap.containsKey("BasicMtlASTWithAssociationView::ASTNode")) 
	BMTLRef_BMTL_ASTNode= (BMTL_ASTNode)inheritanceMap.get("BasicMtlASTWithAssociationView::ASTNode");
else BMTLRef_BMTL_ASTNode= new BMTL_ASTNode(theLib,inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Operation(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_OperationInterface o)
{ super("Operation");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Operation",this);
theLib.recordNewInstance("Operation",theCaller);
if (map.containsKey("BasicMtlASTView::Operation"))
	BMTLRef_BMTL_Operation= (BasicMtlASTView.BMTL_Operation)map.get("BasicMtlASTView::Operation");
else BMTLRef_BMTL_Operation= new BasicMtlASTView.BMTL_Operation((BasicMtlASTView.BMTLLib_BasicMtlASTView)theLib.getRef_BMTLLib_BasicMtlASTView(),map,o);
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
    this.getRef_BMTL_BasicMtlASTView_5fOperation().delete();
    this.getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode().delete();
    theLib.removeInstance("Operation",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Operation getRef_BMTL_BasicMtlASTWithAssociationView_5fOperation()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
public BasicMtlASTView.BMTL_Operation getRef_BMTL_BasicMtlASTView_5fOperation()
{ return this.BMTLRef_BMTL_Operation; }

public BasicMtlASTWithAssociationView.BMTL_ASTNode getRef_BMTL_BasicMtlASTWithAssociationView_5fASTNode()
{ return this.BMTLRef_BMTL_ASTNode; }

private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Operation"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().getRef_BMTL_BasicMtlASTView_5fASTNode ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_instructions ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_instructions ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_instructions (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol396)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_instructions (GenSymbol396)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_Parameters ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_Parameters ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_Parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol397)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_Parameters (GenSymbol397)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_declaredVariables ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_declaredVariables ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_declaredVariables (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol398)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_declaredVariables (GenSymbol398)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_name ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol399)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_name (GenSymbol399)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_throwsExceptionValue ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_throwsExceptionValue ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_throwsExceptionValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface GenSymbol400)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_throwsExceptionValue (GenSymbol400)); }

public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isGetterFor ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_isGetterFor ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isGetterFor (BasicMtlASTView.BMTL_AttributeInterface GenSymbol401)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_isGetterFor (GenSymbol401)); }

public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isSetterFor ()
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().get_BMTL_isSetterFor ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isSetterFor (BasicMtlASTView.BMTL_AttributeInterface GenSymbol402)
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().set_BMTL_isSetterFor (GenSymbol402)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters (BasicMtlASTView.BMTL_VarDeclarationInterface GenSymbol403) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().BMTL_appendParameters (GenSymbol403)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDeclaredVariables (BasicMtlASTView.BMTL_VarDeclarationInterface GenSymbol404) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().BMTL_appendDeclaredVariables (GenSymbol404)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendInstructions (BasicMtlASTView.BMTL_InstructionInterface GenSymbol405) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().BMTL_appendInstructions (GenSymbol405)); }

public Object BMTL_toASTJava () throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fOperation().BMTL_toASTJava ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration ()
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().get_BMTL_decoration ()); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol406)
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().set_BMTL_decoration (GenSymbol406)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol407,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface GenSymbol408,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol409) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewBMTLTypeProperty (GenSymbol407,GenSymbol408,GenSymbol409)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol410,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface GenSymbol411,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol412) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewInheritanceProperty (GenSymbol410,GenSymbol411,GenSymbol412)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol413,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol414,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol415) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_createNewStringProperty (GenSymbol413,GenSymbol414,GenSymbol415)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol416) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_getProperty (GenSymbol416)); }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol417,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface GenSymbol418) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_setProperty (GenSymbol417,GenSymbol418)); }

public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface GenSymbol419) throws Throwable
{ return (getRef_BMTL_BasicMtlASTView_5fASTNode().BMTL_findProperty (GenSymbol419)); }

}
