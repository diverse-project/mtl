package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringType;

import java.io.*;
import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;

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
