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

public class BMTL_Multiplicity extends BMTLObject implements BMTL_MultiplicityInterface {


/*==========================*/
/* DYNAMIC TYPE CONVERSION */
/*==========================*/
public static BMTL_Multiplicity oclAsType(Object o)
{     return (BMTL_Multiplicity)o;  }

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theLib;
private BMTL_MultiplicityInterface theCaller;
private java.util.Hashtable inheritanceMap;
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface BMTL_lowerBound;

org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface BMTL_upperBound;

/*====================*/
/* CLASS CONSTRUCTORS */
/*====================*/
/* Direct constructor */
/*====================*/
public BMTL_Multiplicity(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef)
{ super("Multiplicity");
theLib = libRef;
inheritanceMap=new java.util.Hashtable();
inheritanceMap.put("BasicMtlASTWithAssociationView::Multiplicity",this);
theCaller=this;
theLib.recordNewInstance("Multiplicity",this);

}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTL_Multiplicity(BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface libRef,java.util.Hashtable map,BMTL_MultiplicityInterface o)
{ super("Multiplicity");
theLib = (BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface)libRef.getLibrary();
inheritanceMap = map;
theCaller=o;
map.put("BasicMtlASTWithAssociationView::Multiplicity",this);
theLib.recordNewInstance("Multiplicity",theCaller);
}

/*=================*/
/* CLASS FINALIZER */
/*=================*/
public boolean isValid = true;
public void delete()
{ if (isValid) {
    theLib.removeInstance("Multiplicity",this);
    this.isValid = false;
  }
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BMTLLibInterface getLibrary()
{ return theLib; }
public BasicMtlASTWithAssociationView.BMTL_Multiplicity getRef_BMTL_BasicMtlASTWithAssociationView_5fMultiplicity()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)

{	return theLib.BMTL_allKnownClasses(libName); }
private transient org.irisa.triskell.MT.DataTypes.Java.Type theType;
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ if (this.theType == null) {
    this.theType = this.getLibrary().getMetaClass(new String [] {"Multiplicity"});
  }
  return this.theType; }
/*===================*/
/* INHERITED METHODS */
/*===================*/
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface get_BMTL_lowerBound()
{ return this.BMTL_lowerBound; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_lowerBound (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface value)
{ this.BMTL_lowerBound=value;
return BMTLVoid.TheInstance; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface get_BMTL_upperBound()
{ return this.BMTL_upperBound; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_upperBound (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface value)
{ this.BMTL_upperBound=value;
return BMTLVoid.TheInstance; }

}
