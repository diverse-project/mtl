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

public interface BMTL_BasicMtlLibraryInterface extends BMTLObjectInterface, BasicMtlASTView.BMTL_LibraryInterface{
public BasicMtlASTView.BMTL_BasicMtlLibrary getRef_BMTL_BasicMtlASTView_5fBasicMtlLibrary();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface get_BMTL_parameters();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_parameters (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_appendParameters(BasicMtlASTView.BMTL_ModelRefInterface BMTL_aModel) throws Throwable;
public Object BMTL_toASTJava() throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addClass(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_className) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface BMTL_isRefined(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface BMTL_className,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_refinedClasses) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_addLibraries(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_inheritance,org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_refinedClasses) throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_findRefinedClasses() throws Throwable;
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformInheritedLibrary() throws Throwable;
}
