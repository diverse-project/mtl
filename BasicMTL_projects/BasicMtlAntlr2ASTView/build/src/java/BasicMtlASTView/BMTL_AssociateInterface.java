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

public interface BMTL_AssociateInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_InstructionInterface{
public BasicMtlASTView.BMTL_Associate getRef_BMTL_BasicMtlASTView_5fAssociate();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_roles();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_roles (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_AssociationName();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_AssociationName (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface get_BMTL_isAssociate();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_isAssociate (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendRoles(BasicMtlASTView.BMTL_RoleInterface BMTL_aRole) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
