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

public interface BMTL_IfInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_InstructionInterface{
public BasicMtlASTView.BMTL_If getRef_BMTL_BasicMtlASTView_5fIf();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_condition();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_condition (BasicMtlASTView.BMTL_ExpressionInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_thenBody();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_thenBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_elseBody();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_elseBody (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendThenBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendElseBody(BasicMtlASTView.BMTL_InstructionInterface BMTL_instr) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
