package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType;

public class TypeValueImpl 
    extends PrimitiveValueImpl
    implements TypeValue
{
    private final Type theType;


    public TypeValueImpl(
        boolean isUndefined,
        String errorMessage,
        Type theType)
    {
		this(isUndefined, errorMessage, theType, OclTypeType.TheInstance, OclTypeCommandGroup.TheInstance);
    }

    protected TypeValueImpl(
        boolean isUndefined,
        String errorMessage,
        Type theType,
        Type type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, theType.getQualifiedNameAsString(), type, commands);
		this.theType = theType;
    }

    public Type getTheType()
    {
    	return this.theType;
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs instanceof TypeValue) && ((TypeValue)rhs).getTheType().equals(this.getTheType());
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitTypeValue(this);
    }
}
