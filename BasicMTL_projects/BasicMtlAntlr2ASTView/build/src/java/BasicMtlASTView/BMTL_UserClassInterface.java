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

public interface BMTL_UserClassInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ASTNodeInterface{
public BasicMtlASTView.BMTL_UserClass getRef_BMTL_BasicMtlASTView_5fUserClass();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedMethods();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedMethods (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedAttributes();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedAttributes (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedAttributes(BasicMtlASTView.BMTL_AttributeInterface BMTL_attrib) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedMethods(BasicMtlASTView.BMTL_OperationInterface BMTL_op) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_joinAttributeOperation(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isGetter,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_attributeName,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_operationName) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
