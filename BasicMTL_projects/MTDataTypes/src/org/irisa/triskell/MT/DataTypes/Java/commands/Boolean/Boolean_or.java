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
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Boolean_or extends AbstractCommand {
	public static Boolean_or TheInstance = new Boolean_or();

	private Boolean_or() {
		super("or", new Type [] {BooleanType.TheInstance}, null);
	}

	public Value invoke(Value invoker, Value[] arguments) {
		if (invoker.isUndefined()) {	
			if (arguments[0].isUndefined())
				return invoker;
			else
				return arguments[0];
		} else if (arguments[0].isUndefined()) {
			return invoker;
		} else
			return (((BooleanValue)invoker).getTheBoolean() || ((BooleanValue)arguments[0]).getTheBoolean()) ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
	}
}
