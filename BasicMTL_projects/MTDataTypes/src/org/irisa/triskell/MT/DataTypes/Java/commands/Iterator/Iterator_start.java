/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Iterator_start extends AbstractCommand {
	public static final Iterator_start TheInstance = new Iterator_start();

	protected Iterator_start() {
		super("start", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		((IteratorValueImpl)invoker).reset();
		if (((IteratorValueImpl)invoker).hasNext())
			((IteratorValueImpl)invoker).nextPosition();
		return VoidValueImpl.getTheInstance();
	}
}
