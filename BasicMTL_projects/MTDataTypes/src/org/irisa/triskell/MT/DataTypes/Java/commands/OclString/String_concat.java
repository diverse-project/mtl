/*
 * $Id: String_concat.java,v 1.2 2003-12-09 10:26:27 dvojtise Exp $
 * Created on May 27, 2003
 *
 * implementation of the command concat for the datatype String
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 */
public class String_concat extends AbstractCommand {
	public static final String_concat TheInstance = new String_concat();

	protected String_concat() {
		super("concat", new Type [] {StringType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		if (arguments[0] == null)
			return new StringValueImpl(false, null, ((StringValue)invoker).getTheString() );
		else		
			return new StringValueImpl(false, null, (((StringValue)invoker).getTheString() + ((StringValue)arguments[0]).getTheString()));
		
	}
}
