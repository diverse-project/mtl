/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Real_div extends AbstractCommand {
	public static final  Real_div TheInstance = new Real_div();
	public static final String ZeroDivisionMessage = "Null division error.";
	public static final RealValue PositiveZeroDivisionValue = new RealValueImpl(true, ZeroDivisionMessage, Float.MAX_VALUE);
	public static final RealValue NegativeZeroDivisionValue = new RealValueImpl(true, ZeroDivisionMessage, Float.MIN_VALUE);
	
	public static final RealValue zero = new RealValueImpl(false, null, 0);

	protected Real_div() {
		super("/", new Type [] {RealType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		if (zero.equals(arguments[0]))
			return ((RealValue)invoker).getTheReal() >= 0 ? PositiveZeroDivisionValue : NegativeZeroDivisionValue;
		else
			return new RealValueImpl(false, null, (((RealValue)invoker).getTheReal() / ((RealValue)arguments[0]).getTheReal()));
	}
}
