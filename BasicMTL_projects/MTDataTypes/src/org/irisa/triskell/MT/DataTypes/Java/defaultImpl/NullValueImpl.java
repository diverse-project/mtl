/*
 * $Id: NullValueImpl.java,v 1.2 2004-02-16 17:01:59 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.NullValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;

/**
 * @author ffondeme
 *
 * Default implementation for NullValue
 */
public class NullValueImpl extends ValueImpl implements NullValue {

    protected static final org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl TheInstance = new NullValueImpl();
    public static NullValueImpl getTheInstance () {
        return NullValueImpl.TheInstance;
    }
    
    protected static final Value NullPointerUndefined = new UndefinedValueImpl("Null pointer.");
    public static Value getNullPointerUndefined () {
        return NullValueImpl.NullPointerUndefined;
    }


    private NullValueImpl()
    {
        super(false, null);
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return rhs instanceof NullValue;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitNullValue(this);
    }

	public String getValueAsString() {
		return "<null>";
	}

	public Value invoke(
		String[] scopeQualifiedName,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws UnknownCommandException, MultipleCommandException {
		return NullPointerUndefined;
	}

}
