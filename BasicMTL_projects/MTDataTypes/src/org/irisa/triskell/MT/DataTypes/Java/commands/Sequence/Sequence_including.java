/*
 * $Id: Sequence_including.java,v 1.2 2004-02-16 17:02:23 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Sequence;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

/**
 * @author ffondeme
 *
 * including command for Sequence objects
 */
public class Sequence_including extends AbstractCommand {
	public static final Sequence_including TheInstance = new Sequence_including();

	protected Sequence_including() {
		super("including", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Sequence_append.TheInstance.invokeInternal(invoker, arguments);
	}
}
