/*
 * $Id: OclType_name.java,v 1.2 2004-02-16 17:02:08 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;
/**
 * 
 * Action on a name command
 */
public class OclType_name 
    extends AbstractCommand
{
	public static final OclType_name TheInstance = new OclType_name();

    protected OclType_name()
    {
        super("oclName", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
		return new StringValueImpl(false, null, ((TypeValue)invoker).getTheType().getName());
    }
}
