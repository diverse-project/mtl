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
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class String_toReal extends AbstractCommand {
	public static final String_toReal TheInstance = new String_toReal();

	protected String_toReal() {
		super("toReal", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theString = ((StringValue)invoker).getTheString();
		float theReal = 0.0f;
		String error;
		try {
			theReal = Float.parseFloat(theString);
			error = null;
		} catch (NumberFormatException x) {
			error = x.getMessage();
		}
		return new RealValueImpl(error != null, error, theReal);
	}
}
