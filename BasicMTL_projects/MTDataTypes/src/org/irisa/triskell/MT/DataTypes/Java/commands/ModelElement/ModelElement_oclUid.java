/*
 * $Id: ModelElement_oclUid.java,v 1.2 2004-02-16 17:02:17 dvojtise Exp $
 * @author : ffondemen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

import org.irisa.triskell.MT.DataTypes.Java.ModelElementValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl;

/**
 * 
 * Action to don when receiving a oclUid command
 */
public class ModelElement_oclUid 
    extends AbstractCommand
{
	public static final ModelElement_oclUid TheInstance = new ModelElement_oclUid();

    protected ModelElement_oclUid()
    {
        super("oclUid", new Type[] {}, null);
    }
    
    protected Value invokeInternal(Value invoker, Value[] arguments) {
    	return new StringValueImpl(false, null, ((ModelElementValue)invoker).getTheModelElement());
    }
}
