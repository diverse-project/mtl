/*
 * $Id: Real_max.java,v 1.2 2004-02-16 17:02:01 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 * max command for Real objects
 */
public class Real_max extends AbstractCommand {
	public static final Real_max TheInstance = new Real_max();

	protected Real_max() {
		super("max", new Type [] {RealType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return ((RealValue)invoker).getTheReal() >= ((RealValue)arguments[0]).getTheReal() ? invoker : arguments[0];
	}
}
