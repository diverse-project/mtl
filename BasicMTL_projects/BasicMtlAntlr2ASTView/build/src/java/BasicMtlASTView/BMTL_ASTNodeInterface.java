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

public interface BMTL_ASTNodeInterface extends BMTLObjectInterface{
public BasicMtlASTView.BMTL_ASTNode getRef_BMTL_BasicMtlASTView_5fASTNode();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_decoration();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_decoration (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewBMTLTypeProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewInheritanceProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_createNewStringProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_value,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_tagType) throws Throwable;
public BasicMtlASTView.BMTL_PropertyInterface BMTL_getProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_key,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_value) throws Throwable;
public BasicMtlASTView.BMTL_PropertyInterface BMTL_findProperty(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface BMTL_name) throws Throwable;
}
