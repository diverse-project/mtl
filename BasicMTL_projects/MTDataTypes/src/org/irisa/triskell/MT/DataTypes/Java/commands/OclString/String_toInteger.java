/*
 * $Id: String_toInteger.java,v 1.3 2004-02-16 17:02:07 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;

/**
 * @author ffondeme
 *
 * Action on toInteger command
 */
public class String_toInteger extends AbstractCommand {
	public static final String_toInteger TheInstance = new String_toInteger();

	protected String_toInteger() {
		super("toInteger", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theString = ((StringValue)invoker).getTheString();
		int theInteger = 0;
		String error;
		try {
			theInteger = Integer.parseInt(theString);
			error = null;
		} catch (NumberFormatException x) {
			error = x.getMessage();
		}
		return new IntegerValueImpl(error != null, error, theInteger);
	}
}
