/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Void;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;


public class VoidType extends PrimitiveType {
	public static final Type TheInstance = new VoidType();
	public static final String Name = "Void"; 
	
	private VoidType () {
		super(Name, null);
	}
	
	public boolean isOfType(Value v) {
		return this.isInstance(v);
	}
	
	public boolean isInstance(Value v) {
		return v instanceof VoidValue;
	}

}