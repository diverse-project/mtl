/*
 * $Id: Real_unaryPlus.java,v 1.2 2004-02-16 17:02:01 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

// import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * unaryPlus command for Real objects
 */
public class Real_unaryPlus extends AbstractCommand {
	public static final Real_unaryPlus TheInstance = new Real_unaryPlus();

	protected Real_unaryPlus() {
		super("+", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return invoker;
	}
}
