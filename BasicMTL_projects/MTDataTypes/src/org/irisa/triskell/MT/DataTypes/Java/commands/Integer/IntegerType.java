/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Integer;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealType;


public class IntegerType extends PrimitiveType {
	public static final Type TheInstance = new IntegerType();
	public static final String Name = "Real"; 
	
	private IntegerType () {
		super(Name, new Type[] {RealType.TheInstance});
	}
	
	public boolean isInstance(Value v) {
		return v instanceof RealValue;
	}

}