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

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Integer_idiv extends AbstractCommand {
	public static final  Integer_idiv TheInstance = new Integer_idiv();
	public static final IntegerValue zero = new IntegerValueImpl(false, null, 0);
	public static final String ZeroDivisionMessage = Real_div.ZeroDivisionMessage;
	public static final IntegerValue PositiveZeroDivisionValue = new IntegerValueImpl(true, ZeroDivisionMessage, Integer.MAX_VALUE);
	public static final IntegerValue NegativeZeroDivisionValue = new IntegerValueImpl(true, ZeroDivisionMessage, Integer.MIN_VALUE);

	protected Integer_idiv() {
		super("div", new Type [] {IntegerType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		if (zero.equals(arguments[0]))
			return ((IntegerValue)invoker).getTheInteger() >= 0 ? PositiveZeroDivisionValue : NegativeZeroDivisionValue;
		else
			return new IntegerValueImpl(false, null, (((IntegerValue)invoker).getTheInteger() / ((IntegerValue)arguments[0]).getTheInteger()));
	}
}
