/*
 * Created on May 27, 2003
 * $Id: Collection_notEmpty.java,v 1.1 2004-04-29 08:05:45 ffondeme Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_not;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.CollectionValueImpl;

/**
 * definition of the isEmpty command for Collection
 */
public class Collection_notEmpty extends AbstractCommand {
	public static final Collection_notEmpty TheInstance = new Collection_notEmpty();

	protected Collection_notEmpty() {
		super("notEmpty", new Type [] {}, null);
	}

	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_not.TheInstance.invoke(Collection_isEmpty.TheInstance.invokeInternal(invoker, arguments), null);
	}
}
