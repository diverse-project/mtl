/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Boolean_not extends AbstractCommand {
	public static Boolean_not TheInstance = new Boolean_not();

	protected Boolean_not() {
		super("not", null, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return ((BooleanValue)invoker).getTheBoolean() ? BooleanValueImpl.FALSE : BooleanValueImpl.TRUE;
	}
}
