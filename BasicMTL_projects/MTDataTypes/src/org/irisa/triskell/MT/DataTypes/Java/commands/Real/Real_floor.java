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
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Real_floor extends AbstractCommand {
	public static final Real_floor TheInstance = new Real_floor();

	public Real_floor() {
		super("floor", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
//Personally, I don't like double to float conversion...
//		return new RealValueImpl(false, null, (float)Math.floor(((RealValue)invoker).getTheReal()));
		float theReal = ((RealValue)invoker).getTheReal();
		if (theReal >= 0)
			return new IntegerValueImpl(false, null, (int)(theReal));
		else
			return new IntegerValueImpl(false, null, (int)(theReal-1));
	}
}
