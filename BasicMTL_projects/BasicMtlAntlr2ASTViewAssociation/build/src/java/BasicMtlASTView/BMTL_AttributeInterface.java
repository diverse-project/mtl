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

public interface BMTL_AttributeInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ASTNodeInterface{
public BasicMtlASTView.BMTL_Attribute getRef_BMTL_BasicMtlASTView_5fAttribute();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleGetter();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleGetter (BasicMtlASTView.BMTL_OperationInterface value);
public BasicMtlASTView.BMTL_OperationInterface get_BMTL_possibleSetter();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_possibleSetter (BasicMtlASTView.BMTL_OperationInterface value);
public Object BMTL_toASTJava() throws Throwable;
}
