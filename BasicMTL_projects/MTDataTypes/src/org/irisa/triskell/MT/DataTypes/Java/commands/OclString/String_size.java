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

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class String_size extends AbstractCommand {
	public static final String_size TheInstance = new String_size();

	protected String_size() {
		super("size", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return new IntegerValueImpl(false, null, (((StringValue)invoker).getTheString().length()));
	}
}
