/*
 * Created on May 27, 2003/*
 * $Id: OclAny_toErr.java,v 1.2 2004-02-16 17:01:54 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * @author ffondeme
 *Action whenreceiving a ToErr command 
 */
public class OclAny_toErr extends AbstractCommand {
	public static final OclAny_toErr TheInstance = new OclAny_toErr();

	protected OclAny_toErr() {
		super("toErr", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		System.err.println(invoker);
		return VoidValueImpl.getTheInstance();
	}

	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}

}
