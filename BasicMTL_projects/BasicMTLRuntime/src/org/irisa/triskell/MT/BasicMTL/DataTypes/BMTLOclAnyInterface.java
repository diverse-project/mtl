/*
 * Created on 18 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl;

public interface BMTLOclAnyInterface extends Value, BMTLDataTypeInterface {
	public Value getOclAnyDelegate ();
	
	//=
	public BMTLBooleanInterface BMTL__3d (Value rhs);
	//<>
	public BMTLBooleanInterface BMTL__3c_3e (Value rhs);

	public BMTLBooleanInterface BMTL_oclIsUndefined();

	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type);

	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type);
}
