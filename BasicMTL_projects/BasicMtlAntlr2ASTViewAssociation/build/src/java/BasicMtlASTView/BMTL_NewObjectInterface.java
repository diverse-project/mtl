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

public interface BMTL_NewObjectInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ExpressionInterface{
public BasicMtlASTView.BMTL_NewObject getRef_BMTL_BasicMtlASTView_5fNewObject();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface get_BMTL_arguments();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_arguments (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendArguments(BasicMtlASTView.BMTL_ExpressionInterface BMTL_arg) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
