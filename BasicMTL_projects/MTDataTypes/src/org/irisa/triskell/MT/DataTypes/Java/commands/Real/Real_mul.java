/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Real_mul extends AbstractCommand {
	public static Real_mul TheInstance = new Real_mul();

	private Real_mul() {
		super("*", new Type [] {RealType.TheInstance}, null);
	}

	public Value invoke(Value invoker, Value[] arguments) {
		if (invoker.isUndefined()) {	
			return invoker;
		} else if (arguments[0].isUndefined()) {
			return arguments[0];
		} else
			return new RealValueImpl(false, null, (((RealValue)invoker).getTheReal() * ((RealValue)arguments[0]).getTheReal()));
	}
}
