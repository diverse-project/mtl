/*
 * $Id: OclAny_errorMessage.java,v 1.2 2004-02-16 17:01:56 dvojtise Exp $
 * @author : ffondemen
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * The error message contained by the value.
 * Works for undefined values.
 */
public class OclAny_errorMessage 
    extends AbstractCommand
{
	public static final OclAny_errorMessage TheInstance = new OclAny_errorMessage();

    protected OclAny_errorMessage()
    {
        super("oclErrorMessage", new Type[] {}, null);
    }
    
    public Value invoke(Value invoker, Value[] arguments){
    	return this.invokeInternal(invoker, arguments);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments){
    	String ret = invoker.getErrorMessage();
   		return ret == null ? (Value)NullValueImpl.getTheInstance() : (Value)new StringValueImpl(false, null, ret);
    }
}
