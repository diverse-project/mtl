/*
 * $Id: OclType_allInstances.java,v 1.2 2004-02-16 17:02:08 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;
/**
 * 
 * Action on a allInstances command
 */
public class OclType_allInstances 
    extends AbstractCommand
{
	public static final OclType_allInstances TheInstance = new OclType_allInstances();

    protected OclType_allInstances()
    {
        super("allInstances", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	try {
			return ((TypeValue)invoker).getTheType().allInstances();
    	} catch (Exception x) {
    		return new UndefinedValueImpl(x.getMessage());
    	}
    }
}
