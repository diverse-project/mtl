package org.irisa.triskell.MT.BasicMTL.TopTypes;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;

public interface BMTLInterface extends Value
{
	BMTLBooleanInterface BMTL_isNull(Value v);
}