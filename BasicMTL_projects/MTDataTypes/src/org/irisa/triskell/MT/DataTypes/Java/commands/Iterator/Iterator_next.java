/*
 * Created on May 27, 2003
 * $Id: Iterator_next.java,v 1.2 2004-02-16 17:02:03 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;

/**
 * Defintion of next command for Iterator
 */
public class Iterator_next extends AbstractCommand {
	public static final Iterator_next TheInstance = new Iterator_next();

	protected Iterator_next() {
		super("next", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		((IteratorValueImpl)invoker).nextPosition();
		return VoidValueImpl.getTheInstance();
	}
}
