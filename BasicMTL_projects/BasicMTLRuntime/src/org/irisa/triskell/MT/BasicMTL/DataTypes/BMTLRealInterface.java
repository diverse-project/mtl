/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLRealInterface extends RealValue, BMTLOclAnyInterface {
	RealValue getRealDelegate ();
	
	//-
	BMTLRealInterface BMTL__2d ();
	//+
	BMTLRealInterface BMTL__2b (RealValue r);
	//-
	BMTLRealInterface BMTL__2d (RealValue r);
	//*
	BMTLRealInterface BMTL__2a (RealValue r);
	///
	BMTLRealInterface BMTL__2f (RealValue r);
	
	BMTLRealInterface BMTL_abs ();
	
	BMTLIntegerInterface BMTL_floor ();
	
	BMTLIntegerInterface BMTL_round ();
	
	BMTLRealInterface BMTL_max (RealValue r);
	
	BMTLRealInterface BMTL_min (RealValue r);
	//<
	BMTLBooleanInterface BMTL__3c (RealValue r);
	//>
	BMTLBooleanInterface BMTL__3e (RealValue r);
	//<=
	BMTLBooleanInterface BMTL__3c_3d (RealValue r);
	//>=
	BMTLBooleanInterface BMTL__3e_3d (RealValue r);
	
	BMTLStringInterface BMTL_toString ();
}
