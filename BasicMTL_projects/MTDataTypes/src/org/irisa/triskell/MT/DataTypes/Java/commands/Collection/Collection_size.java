/*
 * Created on May 27, 2003
 * $Id: Collection_size.java,v 1.2 2004-02-16 17:01:53 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;

/**
 * Definition of the size command for Collection
 */
public class Collection_size extends AbstractCommand {
	public static final Collection_size TheInstance = new Collection_size();

	protected Collection_size() {
		super("size", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		int ret;
		if(invoker instanceof CollectionValueImpl) {
			ret = ((CollectionValueImpl)invoker).getTheCollectionAsCollection().size();
		} else {
			ret = ((CollectionValue)invoker).getTheCollection().length;
		}
		return new IntegerValueImpl(false, null, ret);
	}
}
