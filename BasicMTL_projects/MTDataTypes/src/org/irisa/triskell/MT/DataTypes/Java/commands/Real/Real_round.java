/*
 * $Id: Real_round.java,v 1.3 2004-02-16 17:02:02 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Real;

import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

/**
 * @author ffondeme
 *
 * round command for Real objects
 */
public class Real_round extends AbstractCommand {
	public static final Real_round TheInstance = new Real_round();

	protected Real_round() {
		super("round", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return new IntegerValueImpl(false, null, Math.round(((RealValue)invoker).getTheReal()));
	}
}
