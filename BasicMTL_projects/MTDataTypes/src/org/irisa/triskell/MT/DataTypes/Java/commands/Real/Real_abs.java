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
public class Real_abs extends AbstractCommand {
	public static final Real_abs TheInstance = new Real_abs();

	protected Real_abs() {
		super("abs", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		float theReal = ((RealValue)invoker).getTheReal();
		return theReal >= 0 ? invoker : new RealValueImpl(false, null, -theReal);
	}
}
