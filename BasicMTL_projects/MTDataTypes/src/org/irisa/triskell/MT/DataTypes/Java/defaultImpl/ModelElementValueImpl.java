package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import java.io.*;
import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.*;

public class ModelElementValueImpl 
    extends ValueImpl
    implements ModelElementValue
{
    private final String uniqid;


    public ModelElementValueImpl(
        boolean isUndefined,
        String errorMessage,
        String uniqid)
    {
        super(isUndefined, errorMessage);
    	this.uniqid = uniqid;
    }

    public String getTheModelElement()
    {
		return this.uniqid;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof ModelElementValue) && (((ModelElementValue)rhs).getTheModelElement().equals(this.getTheModelElement()));
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitModelElementValue(this);
    }

	public String getValueAsString() {
		return this.getTheModelElement();
	}

}
