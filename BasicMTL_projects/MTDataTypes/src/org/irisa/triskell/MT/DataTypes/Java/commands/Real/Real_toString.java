/*
 * $Id: Real_toString.java,v 1.2 2004-02-16 17:02:02 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * toString command for Real objects
 */
public class Real_toString extends AbstractCommand {
	public static final Real_toString TheInstance = new Real_toString();

	protected Real_toString() {
		super("toString", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theReal = ((RealValue)invoker).getValue();
		return new StringValueImpl(false, null, theReal);
	}
}
