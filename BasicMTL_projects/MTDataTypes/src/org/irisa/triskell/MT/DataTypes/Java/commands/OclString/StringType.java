/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;


public class StringType extends PrimitiveType {
	public static final Type TheInstance = new StringType();
	public static final String Name = "String"; 
	
	private StringType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof StringValue;
	}

}