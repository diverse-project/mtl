package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
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
        super(isUndefined, errorMessage, theType == null ? "<null>" : theType.getQualifiedNameAsString(), type, commands);
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
    
	public Value invoke(String[] scopeQualifiedName, String name, Value[] arguments, String[] discriminants)
		throws UnknownCommandException, MultipleCommandException, SecurityException {
		if (this.isUndefined())
			throw new UnknownCommandException(this,
			        name,
			        arguments,
			        discriminants,
			        this.toString());
		try {
			Type t = this.getTheType();
			if (t != null) {
				Method m = t.getClass().getMethod("invoke", new Class[] {String[].class, String.class, Value[].class, String[].class});
				if (m.getReturnType().equals(Value.class))
					return (Value)m.invoke(t, new Object [] {scopeQualifiedName, name, arguments, discriminants});
			}
		} catch (NoSuchMethodException x) {
		} catch (IllegalAccessException x) {
		} catch (InvocationTargetException x) {
			Throwable cause = x.getTargetException();
			if (cause instanceof UnknownCommandException)
				;
			else if (cause instanceof MultipleCommandException)
				throw (MultipleCommandException)cause;
			else if (cause instanceof SecurityException)
				throw (SecurityException)cause;
			else
				throw new RuntimeException("Internal Error", x);
		}
		return super.invoke(scopeQualifiedName, name, arguments, discriminants);
	}

}
