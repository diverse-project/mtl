/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLIntegerInterface
	extends IntegerValue, BMTLRealInterface {
	IntegerValue getIntegerDelegate();
	
//WARNING the following clashes with the same operation from BMTLRealValueInterface, but with another return type...
	//-
//	BMTLIntegerInterface BMTL__2d ();
	//+
	BMTLIntegerInterface BMTL__2b (IntegerValue i);
	//-
	BMTLIntegerInterface BMTL__2d (IntegerValue i);
	//*
	BMTLIntegerInterface BMTL__2a (IntegerValue i);
//Note: the following does not need to be re-declared in that sense the result is the same as the superclass (Real)
	///
//	BMTLIntegerInterface BMTL__2f (IntegerValue i);
//WARNING the following clashes with the same operation from BMTLRealValueInterface, but with another return type...
//	BMTLIntegerInterface BMTL_abs ();
	
	BMTLIntegerInterface BMTL_div (IntegerValue i);
	
	BMTLIntegerInterface BMTL_mod (IntegerValue i);
	
	BMTLIntegerInterface BMTL_max (IntegerValue i);
	
	BMTLIntegerInterface BMTL_min (IntegerValue i);

	BMTLStringInterface BMTL_toString ();
}
