/*
 * $Id: TupleValueImpl.java,v 1.2 2004-02-16 17:01:59 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.TupleValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * 
 * Default implementation for TupleValue
 */
public class TupleValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.TupleValue
{
    private org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl[] elements = null;
    private transient String toString = null;


    public TupleValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl[] elements)
    {
        super(isUndefined, errorMessage);
		this.elements = elements;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getPart(
        String partName)
    {
    	for (int i = 0; i < this.elements.length; ++i)
    		if (this.elements[i].getName().equals(partName))
    			return this.elements[i].getValue();
    	return new UndefinedValueImpl("No tuple part named " + partName + '.');
    }

    public boolean checkValueEquality(Value rhs)
    {
    	if (! (rhs instanceof TupleValue))
    		return false;
    	String [] partNames = ((TupleValue)rhs).getPartNames();
    	if (! CollectionValueImpl.compareSet(this.getPartNames(), partNames))
    		return false;
    	for (int i = 0; i < partNames.length; ++i)
    		if (! this.getPart(partNames[i]).equals(((TupleValue)rhs).getPart(partNames[i])))
    			return false;
    	return true;
    }

    public String[] getPartNames()
    {
    	TreeSet set = new TreeSet();
    	for (int i = 0; i < this.elements.length; ++i)
    		set.add(this.elements[i].getName());
    	return (String [])set.toArray(new String [set.size()]);
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitTupleValue(this);
    }

	public String getValueAsString() {
		if (this.toString == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("Tuple {");
			Value v;
			for (int i = 0; i < this.elements.length; ++i) {
				sb.append(this.elements[i].getName());
				v = this.elements[i].getValue();
				if (v instanceof ValueImpl) {
					sb.append(" : ");
					sb.append(((ValueImpl)v).getType().getQualifiedNameAsString());
					sb.append(" = ");
					sb.append(((ValueImpl)v).getValueAsString());
				} else {
					sb.append(" = ");
					sb.append(v.toString());
				}
				if (v.isUndefined()) {
					sb.append("/*undefined - */");
					if (v.getErrorMessage() == null)
						sb.append("<no message>");
					else
						sb.append(v.getErrorMessage());
					sb.append(" - */");
				}
			}
			sb.append('}');
			this.toString = sb.toString();
		}
		return this.toString;
	}
	
	public String toString () {
		return this.getValueAsString();
	}

}
