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

public class BMTLLib_BasicMtlASTView extends BMTLLibrary implements BMTLLib_BasicMtlASTViewInterface {

/*==========================*/
/* CLASS PRIVATE ATTRIBUTES */
/*==========================*/
private BMTLLib_BasicMtlASTViewInterface theCaller;
private java.util.Hashtable inheritanceMap;
static final private BMTLString myName=new BMTLString("BasicMtlASTView");
static final private BMTLSetInterface allKnownClasses=initAllKnownClasses();
static private BMTLSetInterface initAllKnownClasses()
{	BMTLSetInterface theSet=new BMTLSet();
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Associate"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Attribute"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("BasicMtlLibrary"),new BMTLString("Library")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("BMTLTypeProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("BooleanLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Catch"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Expression"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("If"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("InheritanceProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Instruction"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("IntLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("JavaCodeLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Library"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Literal"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("ModelRef"),new BMTLString("Library")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("NewObject"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("NullLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("OclAsType"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("OclTypeLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Operation"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("OperationCall"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("RealLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("RepositoryRef"),new BMTLString("ModelRef")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Return"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Role"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("SelfLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("StringLiteral"),new BMTLString("Literal")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("StringProperty"),new BMTLString("Property")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Throws"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("Try"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("TypedModelRef"),new BMTLString("ModelRef")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("UserClass"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("VarCall"),new BMTLString("Expression")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("VarDeclaration"),new BMTLString("ASTNode")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("VarSetting"),new BMTLString("Instruction")}));
	theSet=theSet.BMTL_including(new BMTLOrderedSet(new Value[]{new BMTLString("BasicMtlASTView"),new BMTLString("While"),new BMTLString("Instruction")}));
	return theSet; }
/*=========================*/
/* PARENT(S) REFERENCE(S)  */
/*=========================*/
/*==========================*/
/* CLASS DEFINED FEATURES */
/*==========================*/
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
public org.irisa.triskell.MT.DataTypes.Java.Type theBMTL_5fLibrary;
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
  al.add("BasicMtlASTView");
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
    if (type[0].equals("Library")) return this.theBMTL_5fLibrary;
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
    if (type[0].equals("Standard")) return Standard.BMTLLib_Standard.myType;
    if (type[0].equals("ASTJava")) return ASTJava.BMTLLib_ASTJava.myType;
    return null;
  }
  else {
    String [] unqualifiedType = new String [type.length-1];
    System.arraycopy(type, 1, unqualifiedType, 0, unqualifiedType.length);
    Type ret;
    ret = this.BMTLRef_BMTLLib_Standard.getMetaClass(unqualifiedType);
    if (ret != null) return ret;
    ret = this.BMTLRef_BMTLLib_ASTJava.getMetaClass(unqualifiedType);
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
public ASTJava.BMTLLib_ASTJavaInterface BMTLRef_BMTLLib_ASTJava;
private void buildAllUsedLibs() {
BMTLRef_BMTLLib_Standard=Standard.BMTLLib_Standard.TheInstance;
BMTLRef_BMTLLib_ASTJava=ASTJava.BMTLLib_ASTJava.TheInstance;
}

private void buildAllClassTypes() {
theBMTL_5fAssociate = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Associate"}, BMTL_AssociateInterface.class, BMTL_Associate.class, null);
theBMTL_5fASTNode = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "ASTNode"}, BMTL_ASTNodeInterface.class, BMTL_ASTNode.class, null);
theBMTL_5fAttribute = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Attribute"}, BMTL_AttributeInterface.class, BMTL_Attribute.class, null);
theBMTL_5fBasicMtlLibrary = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "BasicMtlLibrary"}, BMTL_BasicMtlLibraryInterface.class, BMTL_BasicMtlLibrary.class, null);
theBMTL_5fBMTLTypeProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "BMTLTypeProperty"}, BMTL_BMTLTypePropertyInterface.class, BMTL_BMTLTypeProperty.class, null);
theBMTL_5fBooleanLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "BooleanLiteral"}, BMTL_BooleanLiteralInterface.class, BMTL_BooleanLiteral.class, null);
theBMTL_5fCatch = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Catch"}, BMTL_CatchInterface.class, BMTL_Catch.class, null);
theBMTL_5fExpression = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Expression"}, BMTL_ExpressionInterface.class, BMTL_Expression.class, null);
theBMTL_5fIf = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "If"}, BMTL_IfInterface.class, BMTL_If.class, null);
theBMTL_5fInheritanceProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "InheritanceProperty"}, BMTL_InheritancePropertyInterface.class, BMTL_InheritanceProperty.class, null);
theBMTL_5fInstruction = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Instruction"}, BMTL_InstructionInterface.class, BMTL_Instruction.class, null);
theBMTL_5fIntLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "IntLiteral"}, BMTL_IntLiteralInterface.class, BMTL_IntLiteral.class, null);
theBMTL_5fJavaCodeLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "JavaCodeLiteral"}, BMTL_JavaCodeLiteralInterface.class, BMTL_JavaCodeLiteral.class, null);
theBMTL_5fLibrary = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Library"}, BMTL_LibraryInterface.class, BMTL_Library.class, null);
theBMTL_5fLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Literal"}, BMTL_LiteralInterface.class, BMTL_Literal.class, null);
theBMTL_5fModelRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "ModelRef"}, BMTL_ModelRefInterface.class, BMTL_ModelRef.class, null);
theBMTL_5fNewObject = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "NewObject"}, BMTL_NewObjectInterface.class, BMTL_NewObject.class, null);
theBMTL_5fNullLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "NullLiteral"}, BMTL_NullLiteralInterface.class, BMTL_NullLiteral.class, null);
theBMTL_5fOclAsType = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "OclAsType"}, BMTL_OclAsTypeInterface.class, BMTL_OclAsType.class, null);
theBMTL_5fOclTypeLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "OclTypeLiteral"}, BMTL_OclTypeLiteralInterface.class, BMTL_OclTypeLiteral.class, null);
theBMTL_5fOperation = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Operation"}, BMTL_OperationInterface.class, BMTL_Operation.class, null);
theBMTL_5fOperationCall = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "OperationCall"}, BMTL_OperationCallInterface.class, BMTL_OperationCall.class, null);
theBMTL_5fProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Property"}, BMTL_PropertyInterface.class, BMTL_Property.class, null);
theBMTL_5fRealLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "RealLiteral"}, BMTL_RealLiteralInterface.class, BMTL_RealLiteral.class, null);
theBMTL_5fRepositoryRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "RepositoryRef"}, BMTL_RepositoryRefInterface.class, BMTL_RepositoryRef.class, null);
theBMTL_5fReturn = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Return"}, BMTL_ReturnInterface.class, BMTL_Return.class, null);
theBMTL_5fRole = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Role"}, BMTL_RoleInterface.class, BMTL_Role.class, null);
theBMTL_5fSelfLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "SelfLiteral"}, BMTL_SelfLiteralInterface.class, BMTL_SelfLiteral.class, null);
theBMTL_5fStringLiteral = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "StringLiteral"}, BMTL_StringLiteralInterface.class, BMTL_StringLiteral.class, null);
theBMTL_5fStringProperty = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "StringProperty"}, BMTL_StringPropertyInterface.class, BMTL_StringProperty.class, null);
theBMTL_5fThrows = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Throws"}, BMTL_ThrowsInterface.class, BMTL_Throws.class, null);
theBMTL_5fTry = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "Try"}, BMTL_TryInterface.class, BMTL_Try.class, null);
theBMTL_5fTypedModelRef = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "TypedModelRef"}, BMTL_TypedModelRefInterface.class, BMTL_TypedModelRef.class, null);
theBMTL_5fUserClass = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "UserClass"}, BMTL_UserClassInterface.class, BMTL_UserClass.class, null);
theBMTL_5fVarCall = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "VarCall"}, BMTL_VarCallInterface.class, BMTL_VarCall.class, null);
theBMTL_5fVarDeclaration = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "VarDeclaration"}, BMTL_VarDeclarationInterface.class, BMTL_VarDeclaration.class, null);
theBMTL_5fVarSetting = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "VarSetting"}, BMTL_VarSettingInterface.class, BMTL_VarSetting.class, null);
theBMTL_5fWhile = new org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType(this, new String [] {"BasicMtlASTView", "While"}, BMTL_WhileInterface.class, BMTL_While.class, null);
}

/* Direct constructor */
/*====================*/
public BMTLLib_BasicMtlASTView()
{ super("BasicMtlASTView");
inheritanceMap=new java.util.Hashtable();

this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap.put("BasicMtlASTView",this);
theCaller=this;
((BMTLLibraryType)this.getType()).register(theCaller);
}

/* Indirect constructor              */
/* (called from a parent constructor */
/*===================================*/
public BMTLLib_BasicMtlASTView(java.util.Hashtable map,BMTLLib_BasicMtlASTViewInterface o)
{ super("BasicMtlASTView");
this.buildAllUsedLibs();
this.buildAllClassTypes();
inheritanceMap = map;
theCaller=o;
((BMTLLibraryType)this.getType()).register(theCaller);
map.put("BasicMtlASTView",this);
}

/*====================*/
/* Lib & Refs METHODS */
/*====================*/
public BasicMtlASTView.BMTLLib_BasicMtlASTViewInterface getRef_BMTLLib_BasicMtlASTView()
{ return this; }

public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName)
{
	BMTLStringInterface libraryName=(BMTLStringInterface)libName.getTheCollection()[0];
	if (libraryName.equals(myName)) return allKnownClasses;
	return null; }

public static final org.irisa.triskell.MT.DataTypes.Java.Type myType=new BMTLLibraryType("BasicMtlASTView", BMTLLib_BasicMtlASTViewInterface.class, BMTLLib_BasicMtlASTView.class, java.util.Arrays.asList(new BMTLLibraryType [] {}));
public org.irisa.triskell.MT.DataTypes.Java.Type getType()
{ return myType; }

public static final BMTLLib_BasicMtlASTView TheInstance = new BMTLLib_BasicMtlASTView();
/*===================*/
/* INHERITED METHODS */
/*===================*/
}
