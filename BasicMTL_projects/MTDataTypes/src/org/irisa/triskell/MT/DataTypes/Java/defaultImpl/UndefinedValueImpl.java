/*
 * $Id: UndefinedValueImpl.java,v 1.2 2004-02-16 17:01:58 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.*;


public class UndefinedValueImpl extends ValueImpl
{
    public UndefinedValueImpl(
        String errorMessage)
    {
        super(true, errorMessage);
    }

    public boolean checkValueEquality(Value rhs)
    {
		return ! HasValueDeterminer.TheInstance.hasValue(rhs);
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitValue(this);
    }

	public String getValueAsString() {
		return "<undefined>";
	}

}

final class HasValueDeterminer implements ValueVisitor {
	public static final HasValueDeterminer TheInstance = new HasValueDeterminer();
	
	private boolean result = false;
	
	private HasValueDeterminer () {
	}
	
	public synchronized boolean hasValue (Value rhs) {
		this.result = false;
		rhs.accept(this);
		return this.result;
	}

	public void visitBooleanValue(BooleanValue value) {
		this.result = true;
	}

	public void visitCollectionValue(CollectionValue value) {
		this.result = true;
	}

	public void visitEnumValue(EnumValue value) {
		this.result = true;
	}

	public void visitIntegerValue(IntegerValue value) {
		this.result = true;
	}

	public void visitModelElementValue(ModelElementValue value) {
		this.result = true;
	}

	public void visitNullValue(NullValue value) {
		this.result = false;
	}

	public void visitPrimitiveValue(PrimitiveValue value) {
		this.result = true;
	}

	public void visitRealValue(RealValue value) {
		this.result = true;
	}

	public void visitStringValue(StringValue value) {
		this.result = true;
	}

	public void visitTupleValue(TupleValue value) {
		this.result = true;
	}

	public void visitValue(Value value) {
		this.result = false;
	}

	public void visitVoidValue(VoidValue value) {
		this.result = false;
	}

	public void visitTypeValue(TypeValue value) {
		this.result = true;
	}

}
