/*
 * $Id: OclAny_isUndefined.java,v 1.2 2004-02-16 17:01:55 dvojtise Exp $
 * @author : ffondemen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;


/**
 * Obviously, works for undefined values.
 */
public class OclAny_isUndefined 
    extends AbstractCommand
{
	public static final OclAny_isUndefined TheInstance = new OclAny_isUndefined();

    protected OclAny_isUndefined()
    {
        super("oclIsUndefined", new Type[] {}, null);
    }
    
	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}
    
    protected Value invokeInternal(Value invoker, Value[] arguments)
    {
   		return invoker.isUndefined() ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
    }

}
