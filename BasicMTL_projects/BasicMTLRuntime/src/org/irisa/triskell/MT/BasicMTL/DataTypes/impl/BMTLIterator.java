/*
 * Created on 20 août 2003
 * $Id: BMTLIterator.java,v 1.6 2004-02-17 08:58:24 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
//import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_hasNext;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_isOff;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_isOn;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_item;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_next;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_nextItem;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_start;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * @author ffondeme
 * Implementation of the Iterator data type for BasicMTL. It does the wrapping between BasicMTL and MTDataType impoementation (which use commands)
 */
public class BMTLIterator extends BMTLOclAny implements BMTLIteratorInterface {

	/**
	 * @param delegate
	 */
	public BMTLIterator(IteratorValueImpl delegate) {
		super(delegate);
		/* automatically starts the iterator so it is directly usable */
		Iterator_start.TheInstance.invoke(this.getDelegate(), null);
	}

	public IteratorValueImpl getIteratorDelegate() {
		return (IteratorValueImpl)this.getDelegate();
	}

	public BMTLVoidInterface BMTL_start() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(Iterator_start.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLBooleanInterface BMTL_isOff() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_isOff.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLBooleanInterface BMTL_isOn() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_isOn.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLBooleanInterface BMTL_hasNext() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_hasNext.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLOclAnyInterface BMTL_item() {
		return (BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(Iterator_item.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLVoidInterface BMTL_next() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(Iterator_next.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLOclAnyInterface BMTL_nextItem() {
		return (BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(Iterator_nextItem.TheInstance.invoke(this.getDelegate(), null));
	}

}
