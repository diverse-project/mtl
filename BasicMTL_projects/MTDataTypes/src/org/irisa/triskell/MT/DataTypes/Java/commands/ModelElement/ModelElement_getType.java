/*
 * Created on 3 déc. 2003
 * $Id: ModelElement_getType.java,v 1.2 2004-02-16 17:02:17 dvojtise Exp $
 * @author : edrezen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

import org.irisa.triskell.MT.DataTypes.Java.ModelElementValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;

/**
 * @author edrezen
 * Action to do when receiving a getType command
 */
public class ModelElement_getType extends AbstractCommand
{
	public static final ModelElement_getType TheInstance = new ModelElement_getType();

	protected ModelElement_getType  ()
	{
		super("getType", new Type[] {}, null);
	}
    
	protected Value invokeInternal (Value invoker, Value[] arguments) 
	{
		return new TypeValueImpl (false, null, ((ModelElementValue)invoker).getType());
	}

}
