/*
 * $Id: StringValueImpl.java,v 1.3 2004-02-16 17:02:00 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringType;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;

/**
 * 
 * Default implementation for StringValue
 */
public class StringValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.PrimitiveValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.StringValue
{


    public StringValueImpl(
        boolean isUndefined,
        String errorMessage,
        String theString)
    {
		this(isUndefined, errorMessage, theString, StringType.TheInstance, StringCommandGroup.TheInstance);
    }

    protected StringValueImpl(
        boolean isUndefined,
        String errorMessage,
        String theString,
        Type type, 
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, theString, type, commands);
    }

    public String getTheString()
    {
    	return super.getValue();
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof StringValue) && ((StringValue)rhs).getTheString().equals(this.getTheString());
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitStringValue(this);
    }
    
	public String getValueAsString() {
		return "\"" + super.getValueAsString() + '"';
	}

}
