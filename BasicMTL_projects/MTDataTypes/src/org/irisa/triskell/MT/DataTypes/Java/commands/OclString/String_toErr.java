/* 
 * Created on May 27, 2003
 * $Id: String_toErr.java,v 1.2 2004-02-16 17:02:06 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author dvojtise
 * action of the toErr method of String.
 * it differs from the oclany one by printing exactly the string (no type, no carriage return)
 */
public class String_toErr extends AbstractCommand {
	public static final String_toErr TheInstance = new String_toErr();

	protected String_toErr() {
		super("toErr", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		System.err.println(((StringValue)invoker).getTheString());
		return VoidValueImpl.getTheInstance();
	}

	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}

}
