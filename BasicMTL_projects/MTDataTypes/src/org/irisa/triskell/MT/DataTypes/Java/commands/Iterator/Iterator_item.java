/*
 * Created on May 27, 2003
 * $Id: Iterator_item.java,v 1.2 2004-02-16 17:02:04 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * Definition of item command for Iterator
 */
public class Iterator_item extends AbstractCommand {
	public static final Iterator_item TheInstance = new Iterator_item();

	protected Iterator_item() {
		super("item", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return ((IteratorValueImpl)invoker).getCurrent();
	}
}
