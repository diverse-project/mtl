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

public interface BMTL_LibraryInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ASTNodeInterface{
public BasicMtlASTView.BMTL_Library getRef_BMTL_BasicMtlASTView_5fLibrary();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedClasses();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedClasses (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_definedOperations();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_definedOperations (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_name();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_name (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasInheritance();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasInheritance (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_hasAssociation();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_hasAssociation (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedOperations(BasicMtlASTView.BMTL_OperationInterface BMTL_anOp) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendDefinedClasses(BasicMtlASTView.BMTL_UserClassInterface BMTL_aClass) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary() throws Throwable;
}
