package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.UndefinedValueImpl;

abstract public class AbstractCommand 
    implements Command, Serializable
{
    protected final String name;
    protected final Set discriminants;
    protected final Type [] parameters;
    
    private transient String toString;


    public AbstractCommand(
        String name,
        Type [] parameters,
        String[] discriminants)
    {
		this.name = name;
		if (parameters == null)
			this.parameters = new Type [0];
		else {
			this.parameters = new Type [parameters.length];
			System.arraycopy(parameters, 0, this.parameters, 0, parameters.length);
		}
		if (discriminants == null || discriminants.length == 0)
			this.discriminants = null;
		else {
			this.discriminants = new HashSet();
			if (discriminants != null) {
				for (int i = 0; i < discriminants.length; ++i)
					this.discriminants.add(discriminants[i]);
			}
		}
    }

    public String getName()
    {
		return this.name;
    }
    
    public boolean checkArguments (Value [] arguments) {
    	if (arguments == null)
    		return this.parameters.length == 0;
    	if (arguments.length != this.parameters.length)
    		return false;
    	for (int i = 0; i < this.parameters.length; ++i) {
    		if (! ((this.parameters[i] == null) || (arguments[i] instanceof UndefinedValueImpl) || this.parameters[i].isKindOf(arguments[i])))
    			return false;
    	}
    	return true;
    }

    public boolean checkDiscriminants(
        String[] discriminants)
    {
    	if (discriminants != null)
			for (int i = 0; i < discriminants.length; ++i) {
				if (! this.discriminants.contains(discriminants[i]))
					return false;
			}
		return true;
    }

    public boolean isInvokable(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
    {
		return this.checkArguments(arguments) && this.checkDiscriminants(discriminants);
    }

	public String toString() {
		if (this.toString == null) {
			StringBuffer sb = new StringBuffer();
			sb.append(this.getName());
			sb.append('(');
			for (int i = 0; i < this.parameters.length; ++i) {
				if (i != 0) {
					sb.append(',');
					sb.append(' ');
				}
				sb.append(this.parameters[i].toString());
			}
			sb.append(')');
			this.toString = sb.toString();
		}
		return this.toString;
	}
	
	public Value invoke(Value invoker, Value[] arguments) {
		if (invoker.isUndefined())
			return invoker;
		if (arguments != null)
			for (int i = 0; i < arguments.length; ++i)
				if (arguments[i] != null && arguments[i].isUndefined())
					return arguments[i];
		return invokeInternal(invoker, arguments);
	}

	protected abstract Value invokeInternal(Value invoker, Value[] arguments);
}
