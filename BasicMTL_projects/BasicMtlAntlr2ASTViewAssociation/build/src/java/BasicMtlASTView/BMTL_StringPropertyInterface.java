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

public interface BMTL_StringPropertyInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_PropertyInterface{
public BasicMtlASTView.BMTL_StringProperty getRef_BMTL_BasicMtlASTView_5fStringProperty();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface get_BMTL_value();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_value (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_getTheValue() throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_setTheValue(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface BMTL_newValue) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
}
