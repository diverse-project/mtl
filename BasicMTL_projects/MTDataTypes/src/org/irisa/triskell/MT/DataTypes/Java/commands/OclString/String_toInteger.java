/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class String_toInteger extends AbstractCommand {
	public static final String_toInteger TheInstance = new String_toInteger();

	protected String_toInteger() {
		super("toInteger", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theString = ((StringValue)invoker).getTheString();
		int theInteger = 0;
		String error;
		try {
			theInteger = Integer.parseInt(theString);
			error = null;
		} catch (NumberFormatException x) {
			error = x.getMessage();
		}
		return new IntegerValueImpl(error == null, error, theInteger);
	}
}
