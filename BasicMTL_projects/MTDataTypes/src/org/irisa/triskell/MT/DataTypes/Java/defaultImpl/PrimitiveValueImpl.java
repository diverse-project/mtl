package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;

abstract public class PrimitiveValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{
    private String value;
    protected void setValue (String value) {
        this.value = value; 
    }

    protected PrimitiveValueImpl(
        boolean isUndefined,
        String errorMessage,
        String value,
        Type type,
        CommandGroup commandGroup)
    {
        super(isUndefined, errorMessage, type, commandGroup);
    	this.value = value;
    }

    public String getValue()
    {
    	return this.value;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof PrimitiveValue) && (((PrimitiveValue)rhs).getValue().equals(this.getValue()));
    }
    
	public void accept(ValueVisitor visitor) {
		visitor.visitPrimitiveValue(this);
	}

	public String getValueAsString() {
		return this.getValue();
	}

}
