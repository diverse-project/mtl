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
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;


/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class String_add extends AbstractCommand {
	public static final String_add TheInstance = new String_add();

	protected String_add() {
		super("+", new Type [] {StringType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return new StringValueImpl(false, null, (((StringValue)invoker).getTheString() + ((StringValue)arguments[0]).getTheString()));
	}
}
