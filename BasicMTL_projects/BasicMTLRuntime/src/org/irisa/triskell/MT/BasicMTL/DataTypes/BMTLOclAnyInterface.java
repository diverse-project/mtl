/*
 * Created on 18 août 2003
 * $Id: BMTLOclAnyInterface.java,v 1.3 2004-02-17 08:58:23 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 * Definition of accessible methods from BasicMTL on OclAny objects (i.e all objects )
 *  
 */
public interface BMTLOclAnyInterface extends Value, BMTLDataTypeInterface {
	public Value getOclAnyDelegate ();
	
	//=
	public BMTLBooleanInterface BMTL__3d (Value rhs);
	//<>
	public BMTLBooleanInterface BMTL__3c_3e (Value rhs);

	public BMTLBooleanInterface BMTL_oclIsUndefined();

	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type);

	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type);

	public BMTLVoidInterface BMTL_toOut();

	public BMTLVoidInterface BMTL_toErr();
}
