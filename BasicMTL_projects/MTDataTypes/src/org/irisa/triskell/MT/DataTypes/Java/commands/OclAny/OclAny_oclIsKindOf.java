/*
 * $Id: OclAny_oclIsKindOf.java,v 1.2 2004-02-16 17:01:55 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;


/**
 * Obviously, works for undefined values.
 */
public class OclAny_oclIsKindOf 
    extends AbstractCommand
{
	public static final OclAny_oclIsKindOf TheInstance = new OclAny_oclIsKindOf();

    protected OclAny_oclIsKindOf()
    {
        super("oclIsKindOf", new Type[] {OclTypeType.TheInstance}, null);
    }
    
	public Value invoke(Value invoker, Value[] arguments) {
		return this.invokeInternal(invoker, arguments);
	}
    
    protected Value invokeInternal(Value invoker, Value[] arguments)
    {
   		return ((TypeValue)arguments[0]).getTheType().isKindOf(invoker) ? BooleanValueImpl.TRUE : BooleanValueImpl.FALSE;
    }

}
