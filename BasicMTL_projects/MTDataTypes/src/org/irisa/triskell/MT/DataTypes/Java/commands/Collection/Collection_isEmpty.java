/*
 * Created on May 27, 2003
 * $Id: Collection_isEmpty.java,v 1.2 2004-02-16 17:01:53 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

//import java.util.List;
//import java.util.ArrayList;
//import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
//import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
//import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * definition of the isEmpty command for Collection
 */
public class Collection_isEmpty extends AbstractCommand {
	public static final Collection_isEmpty TheInstance = new Collection_isEmpty();

	protected Collection_isEmpty() {
		super("isEmpty", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		boolean ret;
		if(invoker instanceof CollectionValueImpl) {
			ret = ((CollectionValueImpl)invoker).getTheCollectionAsCollection().isEmpty();
		} else {
			ret = ((CollectionValue)invoker).getTheCollection().length > 0;
		}
		return ret ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
	}
}
