/*
 * Created on May 27, 2003
 * $Id: Collection_at.java,v 1.2 2004-02-16 17:01:52 dvojtise Exp $
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
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
// * Definition of the "at" command for Collection 
 */
public class Collection_at extends AbstractCommand {
	public static final Collection_at TheInstance = new Collection_at();

	protected Collection_at() {
		super("at", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		Value v;
		if(invoker instanceof CollectionValueImpl) {
			v = (Value)((CollectionValueImpl)invoker).getTheCollectionAsCollection().toArray()[((IntegerValueImpl)arguments[0]).getTheInteger()-1];
		} else {
			v = ((CollectionValue)invoker).getTheCollection()[((IntegerValueImpl)arguments[0]).getTheInteger()-1];
		}
		return v;
	}
}
