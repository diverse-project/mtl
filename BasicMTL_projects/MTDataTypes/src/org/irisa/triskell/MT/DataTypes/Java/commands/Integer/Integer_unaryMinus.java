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
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Integer_unaryMinus extends AbstractCommand {
	public static final Integer_unaryMinus TheInstance = new Integer_unaryMinus();

	protected Integer_unaryMinus() {
		super("-", new Type [] {}, null);
	}

	public Value invokeInternal(Value invoker, Value[] arguments) {
		return new IntegerValueImpl(false, null, -(((IntegerValue)invoker).getTheInteger()));
	}
}
