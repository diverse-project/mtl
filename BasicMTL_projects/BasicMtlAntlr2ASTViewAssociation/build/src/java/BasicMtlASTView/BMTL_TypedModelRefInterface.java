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

public interface BMTL_TypedModelRefInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_ModelRefInterface{
public BasicMtlASTView.BMTL_TypedModelRef getRef_BMTL_BasicMtlASTView_5fTypedModelRef();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_typeName();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_typeName (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public Object BMTL_toASTJava() throws Throwable;
}
