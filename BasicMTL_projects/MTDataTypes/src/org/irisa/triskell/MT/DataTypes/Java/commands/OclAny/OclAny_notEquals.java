/*
 * $Id: OclAny_notEquals.java,v 1.3 2004-02-16 17:01:56 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclAny;

import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.Boolean_not;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
/**
 * 
 * Action to do when receiving a notEquals action on oclAny objects
 */
public class OclAny_notEquals 
    extends AbstractCommand
{
	public static final OclAny_notEquals TheInstance = new OclAny_notEquals();

    protected OclAny_notEquals()
    {
        super("<>", new Type[] {OclAnyType.TheInstance}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_not.TheInstance.invoke(OclAny_equals.TheInstance.invoke(invoker, arguments), null);
    }
}
