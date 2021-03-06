/*
 * $Id: String_toLowerCase.java,v 1.1 2004-07-08 07:42:04 edrezen Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * @author ffondeme
 *
 * Action on toInteger command
 */
public class String_toLowerCase extends AbstractCommand {
	public static final String_toLowerCase TheInstance = new String_toLowerCase();

	protected String_toLowerCase() {
		super("toLowerCase", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theString = ((StringValue)invoker).getTheString();
		return new StringValueImpl (false,null, theString.toLowerCase()); 
	}
}
