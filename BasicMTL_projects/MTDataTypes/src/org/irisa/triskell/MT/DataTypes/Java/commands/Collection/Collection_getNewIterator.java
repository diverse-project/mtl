/*
 * Created on May 27, 2003
 * $Id: Collection_getNewIterator.java,v 1.2 2004-02-16 17:01:52 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
//import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * @author ffondemeDefinition of the getNewIterator command for Collection
 */
public class Collection_getNewIterator extends AbstractCommand {
	public static final Collection_getNewIterator TheInstance = new Collection_getNewIterator();

	protected Collection_getNewIterator() {
		super("getNewIterator", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return new IteratorValueImpl(false, null, (CollectionValue)invoker);
	}
}
