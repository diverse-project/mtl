/*
 * Created on May 27, 2003
 * $Id: Iterator_start.java,v 1.4 2004-02-16 17:02:04 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author ffondeme
 *
 * action to do when receiving a start
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
