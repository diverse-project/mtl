/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Boolean_or extends AbstractCommand {
	public static Boolean_or TheInstance = new Boolean_or();

	protected Boolean_or() {
		super("or", new Type [] {BooleanType.TheInstance}, null);
	}

	public Value invoke(Value invoker, Value[] arguments) {
		boolean lhsUndef = invoker.isUndefined(), rhsUndef = arguments[0].isUndefined();
		if (lhsUndef && rhsUndef)
			return invoker;
		else if (rhsUndef || lhsUndef) {
			BooleanValue def, undef;
			if (rhsUndef) {
				def = (BooleanValue)invoker; undef = (BooleanValue)arguments[0];
			} else {
				def = (BooleanValue)arguments[0]; undef = (BooleanValue)invoker;
			}
			if (def.getTheBoolean())
				return def;
			else
				return undef;
		} else
			return invokeInternal(invoker, arguments);
	}
	
	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return (((BooleanValue)invoker).getTheBoolean() || ((BooleanValue)arguments[0]).getTheBoolean()) ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
	}
}
