/* 
 * $Id: String_toOut.java,v 1.1 2003-11-24 13:38:29 dvojtise Exp $
 * Created on May 27, 2003
 *
 **/
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author dvojtise
 * action of the toOut method of String.
 * it differs from the oclany one by printing exactly the string (no type, no carriage return)
 */
public class String_toOut extends AbstractCommand {
	public static final String_toOut TheInstance = new String_toOut();

	protected String_toOut() {
		super("toOut", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		System.out.println(((StringValue)invoker).getTheString());
		return VoidValueImpl.getTheInstance();
	}

	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}

}
