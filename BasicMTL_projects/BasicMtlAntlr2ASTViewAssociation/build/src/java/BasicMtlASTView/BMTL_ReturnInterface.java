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

public interface BMTL_ReturnInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_InstructionInterface{
public BasicMtlASTView.BMTL_Return getRef_BMTL_BasicMtlASTView_5fReturn();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public BasicMtlASTView.BMTL_ExpressionInterface get_BMTL_returnedExpression();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_returnedExpression (BasicMtlASTView.BMTL_ExpressionInterface value);
public Object BMTL_toASTJava() throws Throwable;
}
