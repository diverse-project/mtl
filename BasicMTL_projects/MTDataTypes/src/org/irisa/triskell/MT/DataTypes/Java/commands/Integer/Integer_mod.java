/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Integer;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.Real_div;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;

public class Integer_mod extends AbstractCommand {
	public static final Integer_mod TheInstance = new Integer_mod();
	public static final IntegerValue zero = Integer_idiv.zero;
	public static final String ZeroDivisionMessage = Real_div.ZeroDivisionMessage;
	public static final IntegerValue PositiveZeroDivisionValue = Integer_idiv.PositiveZeroDivisionValue;
	public static final IntegerValue NegativeZeroDivisionValue = Integer_idiv.NegativeZeroDivisionValue;

	protected Integer_mod() {
		super("mod", new Type [] {IntegerType.TheInstance}, null);
	}

	/**
	 * A slight difference against OCL: X.mod(0) is undefined and 0.mod(X) is 0, even if X is 0...
	 */
	protected Value invokeInternal(Value invoker, Value[] arguments) {
		if (zero.equals(arguments[0])) {
			if (zero.equals(invoker))
				return zero;
			else
				return ((IntegerValue)invoker).getTheReal() >= 0 ? PositiveZeroDivisionValue : NegativeZeroDivisionValue;
		} else {
			
			return new IntegerValueImpl(false, null, (((IntegerValue)invoker).getTheInteger() % ((IntegerValue)arguments[0]).getTheInteger()));
		}
	}
}
