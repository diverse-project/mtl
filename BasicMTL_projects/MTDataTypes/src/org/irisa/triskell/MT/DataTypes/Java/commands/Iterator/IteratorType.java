/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;


public class IteratorType extends PrimitiveType {
	public static final Type TheInstance = new IteratorType();
	public static final String Name = "Iterator"; 
	
	private IteratorType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof IteratorValueImpl;
	}

}