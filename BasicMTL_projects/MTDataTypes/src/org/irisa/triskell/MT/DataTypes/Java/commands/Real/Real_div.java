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
public class Real_div extends AbstractCommand {
	public static Real_div TheInstance = new Real_div();

	private Real_div() {
		super("/", new Type [] {RealType.TheInstance}, null);
	}

	public Value invoke(Value invoker, Value[] arguments) {
		if (invoker.isUndefined()) {	
			return invoker;
		} else if (arguments[0].isUndefined()) {
			return arguments[0];
//		} else if (arguments[0].equals(new RealValueImpl(false, null, (float)0.0))) {
//			return new RealValueImpl(true, "Null division error", (((RealValue)invoker).getTheReal() / ((RealValue)arguments[0]).getTheReal()));
		} else
			return new RealValueImpl(false, null, (((RealValue)invoker).getTheReal() / ((RealValue)arguments[0]).getTheReal()));
	}
}
