/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class String_substring extends AbstractCommand {
	public static final String_substring TheInstance = new String_substring();

	protected String_substring() {
		super("substring", new Type [] {IntegerType.TheInstance, IntegerType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		int lower = ((IntegerValue)arguments[0]).getTheInteger();
		int upper = ((IntegerValue)arguments[1]).getTheInteger();
		String theString = ((StringValue)invoker).getTheString();
		int correctLower = Math.max(lower, 1);
		int correctUpper = Math.min(upper, theString.length());
		correctLower = Math.min(correctLower, correctUpper);
		correctUpper = Math.max(correctLower, correctUpper);
		String error;
		if (lower == correctLower && upper == correctUpper)
			error = null;
		else
			error = "Incorrect bounds for string substring.";
		return new StringValueImpl(error != null, error, theString.substring(correctLower-1, correctUpper));
	}
}
