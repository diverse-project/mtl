/*
 * $Id: OrderedSet_including.java,v 1.2 2004-02-16 17:02:14 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;

/**
 * @author ffondeme
 *
 * Action when receiving an including command 
 */
public class OrderedSet_including extends AbstractCommand {
	public static final OrderedSet_including TheInstance = new OrderedSet_including();

	protected OrderedSet_including() {
		super("including", new Type [] {OclAnyType.TheInstance}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return OrderedSet_append.TheInstance.invokeInternal(invoker, arguments);
	}
}
