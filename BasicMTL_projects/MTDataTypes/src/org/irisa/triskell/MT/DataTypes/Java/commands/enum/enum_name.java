/*
 * Created on May 27, 2003
 * $Id: enum_name.java,v 1.2 2004-02-16 17:02:15 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.enum;

import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * Definition of the name command for Enum object
 */
public class enum_name extends AbstractCommand {
	public static final enum_name TheInstance = new enum_name();

	protected enum_name() {
		super("name", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		String theName = ((EnumValue)invoker).getTheEnum();
		return new StringValueImpl(false, null, theName);
	}
}
