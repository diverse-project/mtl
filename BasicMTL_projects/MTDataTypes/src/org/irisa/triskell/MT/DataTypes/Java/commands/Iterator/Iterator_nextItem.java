/*
 * Created on May 27, 2003
 * $Id: Iterator_nextItem.java,v 1.2 2004-02-16 17:02:03 dvojtise Exp $
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
 * Definition of nextItem command for Iterator
 */
public class Iterator_nextItem extends AbstractCommand {
	public static final Iterator_nextItem TheInstance = new Iterator_nextItem();

	protected Iterator_nextItem() {
		super("nextItem", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return ((IteratorValueImpl)invoker).next();
	}
}
