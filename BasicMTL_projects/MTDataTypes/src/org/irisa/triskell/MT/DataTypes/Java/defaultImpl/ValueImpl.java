/*
 * $Id: ValueImpl.java,v 1.2 2004-02-16 17:01:57 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.*;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * 
 * Default implementation for Value  (inherited by other default implementation)
 */
abstract public class ValueImpl extends Throwable
    implements org.irisa.triskell.MT.DataTypes.Java.Value, java.io.Serializable
{
    private final boolean undefined;
    private final String errorMessage;
    private final Type type;
    private final CommandGroup commands;

    public ValueImpl(
        boolean isUndefined,
        String errorMessage)
    {
    	this(isUndefined, errorMessage, OclAnyType.TheInstance, OclAnyCommandGroup.TheInstance);
    }
    
    protected ValueImpl(
        boolean isUndefined,
        String errorMessage,
        Type type,
        CommandGroup commmands) {
    	this.undefined = isUndefined;
    	this.errorMessage = errorMessage;
    	this.type = type;
    	this.commands = commmands;
    }

    public boolean isUndefined()
    {
    	return this.undefined;
    }

    public String getErrorMessage()
    {
    	return this.errorMessage;
    }

	public Type getType() {
		return type;
	}

    public boolean equals(Object rhs) {
    	return (this == rhs) || ((rhs instanceof Value) && this.equals((Value)rhs));
    }
    
    protected boolean checkUndefinedEquality (Value rhs) {
    	if (this.isUndefined()) {
    		if (rhs.isUndefined()) {
				String rem = rhs.getErrorMessage(), tem = this.getErrorMessage();
				if (tem == null || tem.length() == 0)
					return rem == null || rem.length() == 0;
				else
					return tem.equals(rem);
    		} else
    			return false;
    	} else
    		return ! rhs.isUndefined();
    }
    
    protected boolean checkValueEquality (Value rhs) {
    	return true;
    }

    public final boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)  {
        return this == rhs || (rhs != null && this.checkUndefinedEquality(rhs) && this.checkValueEquality(rhs));
    }
    
    public CommandGroup getCommands () {
    	return this.commands;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws UnknownCommandException, MultipleCommandException
    {
		return this.getCommands().invoke(scopeQualifiedName, this, name, arguments, discriminants);
    }
	
	//Commands implementations
	public Value bmtl_equals (Value v) {
		return OclAny_equals.TheInstance.invoke(this, new Value[] {v});
	}
	
	public Value bmtl_notEquals (Value v) {
		return OclAny_notEquals.TheInstance.invoke(this, new Value[] {v});
	}
	
	public abstract String getValueAsString();
	
	public String toString () {
		StringBuffer sbuf = new StringBuffer();
		if (this.isUndefined()) {
			sbuf.append("undefined ");
			String em = this.getErrorMessage();
			if (em != null) {
				sbuf.append(" because of ");
				sbuf.append(em);
				sbuf.append(' ');
			}
		}
		sbuf.append(this.getValueAsString());
		sbuf.append(" : ");
		sbuf.append(this.getType().toString());
		return sbuf.toString();
	}

}
