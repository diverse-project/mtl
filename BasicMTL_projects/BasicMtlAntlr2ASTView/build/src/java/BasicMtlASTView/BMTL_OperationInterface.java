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

public interface BMTL_OperationInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ASTNodeInterface{
public BasicMtlASTView.BMTL_Operation getRef_BMTL_BasicMtlASTView_5fOperation();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_instructions();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_instructions (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_Parameters();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_Parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_declaredVariables();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_declaredVariables (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_throwsExceptionValue();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_throwsExceptionValue (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value);
public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isGetterFor();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isGetterFor (BasicMtlASTView.BMTL_AttributeInterface value);
public BasicMtlASTView.BMTL_AttributeInterface get_BMTL_isSetterFor();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isSetterFor (BasicMtlASTView.BMTL_AttributeInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters(BasicMtlASTView.BMTL_VarDeclarationInterface BMTL_param) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDeclaredVariables(BasicMtlASTView.BMTL_VarDeclarationInterface BMTL_var) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendInstructions(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
