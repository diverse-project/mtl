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

public class BMTLLib_BasicMtlASTWithAssociationView extends BMTLLibrary implements BMTLLib_BasicMtlASTWithAssociationViewInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTWithAssociationViewInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("BasicMtlASTWithAssociationView");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Association"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("EndPoint"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Library"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Multiplicity")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Associate"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Attribute"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("BasicMtlLibrary"),new BMTLString("Library")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("BMTLTypeProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("BooleanLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Catch"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Expression"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("If"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("InheritanceProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Instruction"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("IntLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("JavaCodeLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Literal"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("ModelRef"),new BMTLString("Library")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("NewObject"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("NullLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("OclAsType"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("OclTypeLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Operation"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("OperationCall"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("RealLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("RepositoryRef"),new BMTLString("ModelRef")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Return"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Role"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("SelfLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("StringLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("StringProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Throws"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("Try"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("TypedModelRef"),new BMTLString("ModelRef")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("UserClass"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("VarCall"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("VarDeclaration"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("VarSetting"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTWithAssociationView"),new BMTLString("While"),new BMTLString("Instruction")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
public BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface BMTLRef_BMTLLib_BasicMtlASTView;
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fAssociation;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fEndPoint;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fLibrary;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fMultiplicity;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fAssociate;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fASTNode;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fAttribute;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fBasicMtlLibrary;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fBMTLTypeProperty;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fBooleanLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fCatch;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fExpression;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fIf;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fInheritanceProperty;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fInstruction;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fIntLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fJavaCodeLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fModelRef;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fNewObject;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fNullLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fOclAsType;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fOclTypeLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fOperation;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fOperationCall;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fProperty;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fRealLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fRepositoryRef;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fReturn;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fRole;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fSelfLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fStringLiteral;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fStringProperty;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fThrows;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fTry;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fTypedModelRef;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fUserClass;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fVarCall;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fVarDeclaration;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fVarSetting;
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fWhile;
static {
  java.util.LinkedList al = new java.util.LinkedList();
  al.add("BasicMtlASTWithAssociationView");
  al.addAll(BasicMtlASTView.BMTLLib_BasicMtlASTView.CompatibleNames);
  CompatibleNames = al;
}

public static final java.util.List CompatibleNames;
public org.irisa.triskell.MT.DataTypes.Java.Type getMetaClass(String [] type)
{ if (type == null || type.length == 0)
    return null;
  if (CompatibleNames.contains(type[0])) {
   if (type.length > 2)
     return null;
   else if (type.length == 2)
    return this.getMetaClass(new String [] {type[1]});
   else //if (type.length == 1)
    return this.getType();
  }
  if (type.length == 1) {
    if (type[0].equals("Association")) return this.theBMTL_5fAssociation;
    if (type[0].equals("EndPoint")) return this.theBMTL_5fEndPoint;
    if (type[0].equals("Library")) return this.theBMTL_5fLibrary;
    if (type[0].equals("Multiplicity")) return this.theBMTL_5fMultiplicity;
    if (type[0].equals("Associate")) return this.theBMTL_5fAssociate;
    if (type[0].equals("ASTNode")) return this.theBMTL_5fASTNode;
    if (type[0].equals("Attribute")) return this.theBMTL_5fAttribute;
    if (type[0].equals("BasicMtlLibrary")) return this.theBMTL_5fBasicMtlLibrary;
    if (type[0].equals("BMTLTypeProperty")) return this.theBMTL_5fBMTLTypeProperty;
    if (type[0].equals("BooleanLiteral")) return this.theBMTL_5fBooleanLiteral;
    if (type[0].equals("Catch")) return this.theBMTL_5fCatch;
    if (type[0].equals("Expression")) return this.theBMTL_5fExpression;
    if (type[0].equals("If")) return this.theBMTL_5fIf;
    if (type[0].equals("InheritanceProperty")) return this.theBMTL_5fInheritanceProperty;
    if (type[0].equals("Instruction")) return this.theBMTL_5fInstruction;
    if (type[0].equals("IntLiteral")) return this.theBMTL_5fIntLiteral;
    if (type[0].equals("JavaCodeLiteral")) return this.theBMTL_5fJavaCodeLiteral;
    if (type[0].equals("Literal")) return this.theBMTL_5fLiteral;
    if (type[0].equals("ModelRef")) return this.theBMTL_5fModelRef;
    if (type[0].equals("NewObject")) return this.theBMTL_5fNewObject;
    if (type[0].equals("NullLiteral")) return this.theBMTL_5fNullLiteral;
    if (type[0].equals("OclAsType")) return this.theBMTL_5fOclAsType;
    if (type[0].equals("OclTypeLiteral")) return this.theBMTL_5fOclTypeLiteral;
    if (type[0].equals("Operation")) return this.theBMTL_5fOperation;
    if (type[0].equals("OperationCall")) return this.theBMTL_5fOperationCall;
    if (type[0].equals("Property")) return this.theBMTL_5fProperty;
    if (type[0].equals("RealLiteral")) return this.theBMTL_5fRealLiteral;
    if (type[0].equals("RepositoryRef")) return this.theBMTL_5fRepositoryRef;
    if (type[0].equals("Return")) return this.theBMTL_5fReturn;
    if (type[0].equals("Role")) return this.theBMTL_5fRole;
    if (type[0].equals("SelfLiteral")) return this.theBMTL_5fSelfLiteral;
    if (type[0].equals("StringLiteral")) return this.theBMTL_5fStringLiteral;
    if (type[0].equals("StringProperty")) return this.theBMTL_5fStringProperty;
    if (type[0].equals("Throws")) return this.theBMTL_5fThrows;
    if (type[0].equals("Try")) return this.theBMTL_5fTry;
    if (type[0].equals("TypedModelRef")) return this.theBMTL_5fTypedModelRef;
    if (type[0].equals("UserClass")) return this.theBMTL_5fUserClass;
    if (type[0].equals("VarCall")) return this.theBMTL_5fVarCall;
    if (type[0].equals("VarDeclaration")) return this.theBMTL_5fVarDeclaration;
    if (type[0].equals("VarSetting")) return this.theBMTL_5fVarSetting;
    if (type[0].equals("While")) return this.theBMTL_5fWhile;
    Type ret;
    java.util.ArrayList found = new java.util.ArrayList(1);
    ret = this.getRef_BMTLLib_BasicMtlASTView().getMetaClass(type);
    if (ret != null) {
      found.add("BasicMtlASTView");
    }
    if (found.size() == 1)
      return ret;
    else if (found.size() > 1)
      throw new RuntimeException("Cannot choose metatype " + type[0] + " present in the inherited libraries " + AWK.mergeCollection(found, ", "));
    if (type[0].equals("Standard")) return Standard.BMTLLib_Standard.myType;
    return null;
  }
  else {
    String [] unqualifiedType = new String [type.length-1];
    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);
    Type ret;
    ret = this.BMTLRef_BMTLLib_Standard.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    return null;
  }
}

public BMTLLibInterface getLibrary() {
  return (BMTLLibrary)this.theCaller;
}

/*===========================*/
/* LIBRARYCLASS CONSTRUCTORS */
/*===========================*/
public Standard.BMTLLib_StandardInterface BMTLRef_BMTLLib_Standard;
private void buildAllUsedLibs() {
BMTLRef_BMTLLib_Standard=Standard.BMTLLib_Standard.TheInstance;
}

private void buildAllClassTypes() {
theBMTL_5fAssociation = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Association"}, BMTL_AssociationInterface.class, BMTL_Association.class, null);
theBMTL_5fEndPoint = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "EndPoint"}, BMTL_EndPointInterface.class, BMTL_EndPoint.class, null);
theBMTL_5fLibrary = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Library"}, BMTL_LibraryInterface.class, BMTL_Library.class, null);
theBMTL_5fMultiplicity = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Multiplicity"}, BMTL_MultiplicityInterface.class, BMTL_Multiplicity.class, null);
theBMTL_5fAssociate = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Associate"}, BMTL_AssociateInterface.class, BMTL_Associate.class, null);
theBMTL_5fASTNode = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "ASTNode"}, BMTL_ASTNodeInterface.class, BMTL_ASTNode.class, null);
theBMTL_5fAttribute = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Attribute"}, BMTL_AttributeInterface.class, BMTL_Attribute.class, null);
theBMTL_5fBasicMtlLibrary = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "BasicMtlLibrary"}, BMTL_BasicMtlLibraryInterface.class, BMTL_BasicMtlLibrary.class, null);
theBMTL_5fBMTLTypeProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "BMTLTypeProperty"}, BMTL_BMTLTypePropertyInterface.class, BMTL_BMTLTypeProperty.class, null);
theBMTL_5fBooleanLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "BooleanLiteral"}, BMTL_BooleanLiteralInterface.class, BMTL_BooleanLiteral.class, null);
theBMTL_5fCatch = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Catch"}, BMTL_CatchInterface.class, BMTL_Catch.class, null);
theBMTL_5fExpression = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Expression"}, BMTL_ExpressionInterface.class, BMTL_Expression.class, null);
theBMTL_5fIf = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "If"}, BMTL_IfInterface.class, BMTL_If.class, null);
theBMTL_5fInheritanceProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "InheritanceProperty"}, BMTL_InheritancePropertyInterface.class, BMTL_InheritanceProperty.class, null);
theBMTL_5fInstruction = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Instruction"}, BMTL_InstructionInterface.class, BMTL_Instruction.class, null);
theBMTL_5fIntLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "IntLiteral"}, BMTL_IntLiteralInterface.class, BMTL_IntLiteral.class, null);
theBMTL_5fJavaCodeLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "JavaCodeLiteral"}, BMTL_JavaCodeLiteralInterface.class, BMTL_JavaCodeLiteral.class, null);
theBMTL_5fLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Literal"}, BMTL_LiteralInterface.class, BMTL_Literal.class, null);
theBMTL_5fModelRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "ModelRef"}, BMTL_ModelRefInterface.class, BMTL_ModelRef.class, null);
theBMTL_5fNewObject = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "NewObject"}, BMTL_NewObjectInterface.class, BMTL_NewObject.class, null);
theBMTL_5fNullLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "NullLiteral"}, BMTL_NullLiteralInterface.class, BMTL_NullLiteral.class, null);
theBMTL_5fOclAsType = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "OclAsType"}, BMTL_OclAsTypeInterface.class, BMTL_OclAsType.class, null);
theBMTL_5fOclTypeLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "OclTypeLiteral"}, BMTL_OclTypeLiteralInterface.class, BMTL_OclTypeLiteral.class, null);
theBMTL_5fOperation = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Operation"}, BMTL_OperationInterface.class, BMTL_Operation.class, null);
theBMTL_5fOperationCall = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "OperationCall"}, BMTL_OperationCallInterface.class, BMTL_OperationCall.class, null);
theBMTL_5fProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Property"}, BMTL_PropertyInterface.class, BMTL_Property.class, null);
theBMTL_5fRealLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "RealLiteral"}, BMTL_RealLiteralInterface.class, BMTL_RealLiteral.class, null);
theBMTL_5fRepositoryRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "RepositoryRef"}, BMTL_RepositoryRefInterface.class, BMTL_RepositoryRef.class, null);
theBMTL_5fReturn = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Return"}, BMTL_ReturnInterface.class, BMTL_Return.class, null);
theBMTL_5fRole = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Role"}, BMTL_RoleInterface.class, BMTL_Role.class, null);
theBMTL_5fSelfLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "SelfLiteral"}, BMTL_SelfLiteralInterface.class, BMTL_SelfLiteral.class, null);
theBMTL_5fStringLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "StringLiteral"}, BMTL_StringLiteralInterface.class, BMTL_StringLiteral.class, null);
theBMTL_5fStringProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "StringProperty"}, BMTL_StringPropertyInterface.class, BMTL_StringProperty.class, null);
theBMTL_5fThrows = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Throws"}, BMTL_ThrowsInterface.class, BMTL_Throws.class, null);
theBMTL_5fTry = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "Try"}, BMTL_TryInterface.class, BMTL_Try.class, null);
theBMTL_5fTypedModelRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "TypedModelRef"}, BMTL_TypedModelRefInterface.class, BMTL_TypedModelRef.class, null);
theBMTL_5fUserClass = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "UserClass"}, BMTL_UserClassInterface.class, BMTL_UserClass.class, null);
theBMTL_5fVarCall = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "VarCall"}, BMTL_VarCallInterface.class, BMTL_VarCall.class, null);
theBMTL_5fVarDeclaration = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "VarDeclaration"}, BMTL_VarDeclarationInterface.class, BMTL_VarDeclaration.class, null);
theBMTL_5fVarSetting = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "VarSetting"}, BMTL_VarSettingInterface.class, BMTL_VarSetting.class, null);
theBMTL_5fWhile = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTWithAssociationView", "While"}, BMTL_WhileInterface.class, BMTL_While.class, null);
}

/* Direct constructor */
/*====================*/
public BMTLLib_BasicMtlASTWithAssociationView()
{ super("BasicMtlASTWithAssociationView");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("BasicMtlASTWithAssociationView",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
if (inheritanceMap.containsKey("BasicMtlASTView"))
	BMTLRef_BMTLLib_BasicMtlASTView= (BasicMtlASTView.BMTLLib_BasicMtlASTView)inheritanceMap.get("BasicMtlASTView");
else BMTLRef_BMTLLib_BasicMtlASTView= new BasicMtlASTView.BMTLLib_BasicMtlASTView(inheritanceMap,this);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_BasicMtlASTWithAssociationView(java.util.Hashtable map,BMTLLib_BasicMtlASTWithAssociationViewInterface o)
{ super("BasicMtlASTWithAssociationView");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("BasicMtlASTWithAssociationView",this);
if (map.containsKey("BasicMtlASTView"))
	BMTLRef_BMTLLib_BasicMtlASTView= (BasicMtlASTView.BMTLLib_BasicMtlASTView)map.get("BasicMtlASTView");
else BMTLRef_BMTLLib_BasicMtlASTView= new BasicMtlASTView.BMTLLib_BasicMtlASTView(map,o);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BasicMtlASTWithAssociationView.BMTLLib_BasicMtlASTWithAssociationViewInterface getRef_BMTLLib_BasicMtlASTWithAssociationView()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLSetInterface allClasses=null;
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
    allClasses = this.BMTLRef_BMTLLib_BasicMtlASTView.BMTL_allKnownClasses(libName);
	if (allClasses != null) return allClasses;
	return null; }

public BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface getRef_BMTLLib_BasicMtlASTView()
{ return this.BMTLRef_BMTLLib_BasicMtlASTView; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("BasicMtlASTWithAssociationView", BMTLLib_BasicMtlASTWithAssociationViewInterface.class, BMTLLib_BasicMtlASTWithAssociationView.class, java.util.Arrays.asList(new BMTLLibraryType [] {(BMTLLibraryType)BasicMtlASTView.BMTLLib_BasicMtlASTView.myType}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_BasicMtlASTWithAssociationView TheInstance = new BMTLLib_BasicMtlASTWithAssociationView();
/*===================*/
/* INHERITED METHODS */
/*===================*/
}
