/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLBooleanInterface extends BooleanValue, BMTLOclAnyInterface {
	BooleanValue getBooleanDelegate();
	
	BMTLBooleanInterface BMTL_not();
	
	BMTLBooleanInterface BMTL_or(BooleanValue rhs);
	
	BMTLBooleanInterface BMTL_xor(BooleanValue rhs);
	
	BMTLBooleanInterface BMTL_and(BooleanValue rhs);
	
	BMTLBooleanInterface BMTL_implies(BooleanValue rhs);
}
