package BasicMtlASTWithAssociationView;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface BMTL_MultiplicityInterface extends BMTLObjectInterface{
public BasicMtlASTWithAssociationView.BMTL_Multiplicity getRef_BMTL_BasicMtlASTWithAssociationView_5fMultiplicity();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface get_BMTL_lowerBound();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_lowerBound (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface value);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface get_BMTL_upperBound();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface set_BMTL_upperBound (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface value);
}
