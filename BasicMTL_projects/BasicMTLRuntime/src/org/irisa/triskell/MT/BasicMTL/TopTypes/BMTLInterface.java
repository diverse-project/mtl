package org.irisa.triskell.MT.BasicMTL.TopTypes;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;

public interface BMTLInterface extends Value
{
	BMTLBooleanInterface BMTL_isNull(Value v);
	BMTLSetInterface BMTL_newSet();
	BMTLBagInterface BMTL_newBag();
	BMTLSequenceInterface BMTL_newSequence();
	BMTLOrderedSetInterface BMTL_newOrderedSet();
}