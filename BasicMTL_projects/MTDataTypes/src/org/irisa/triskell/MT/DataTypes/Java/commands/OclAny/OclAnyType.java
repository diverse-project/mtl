/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Void.VoidType;


public class OclAnyType extends PrimitiveType {
	public static final Type TheInstance = new OclAnyType();
	public static final String Name = "OclAny"; 
	
	private OclAnyType () {
		super(Name, null);
	}
	
	public boolean isInstance(Value v) {
		return (! VoidType.TheInstance.isOfType(v)) && (! (v instanceof CollectionValue));
	}

	public boolean conformsTo(Type type) throws UnsupportedOperationException {
		return this.equals(type);
	}

}