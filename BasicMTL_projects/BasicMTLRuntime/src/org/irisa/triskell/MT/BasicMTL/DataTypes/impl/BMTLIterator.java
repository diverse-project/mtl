/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_hasNext;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_isOff;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_isOn;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_item;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_next;
import org.irisa.triskell.MT.DataTypes.Java.commands.Iterator.Iterator_start;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLIterator extends BMTLOclAny implements BMTLIteratorInterface {

	/**
	 * @param delegate
	 */
	public BMTLIterator(IteratorValueImpl delegate) {
		super(delegate);
	}

	public IteratorValueImpl getIteratorDelegate() {
		return (IteratorValueImpl)this.getDelegate();
	}

	public BMTLVoidInterface BMTL_start() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(Iterator_start.TheInstance.invoke(this, null));
	}

	public BMTLBooleanInterface BMTL_isOff() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_isOff.TheInstance.invoke(this, null));
	}

	public BMTLBooleanInterface BMTL_isOn() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_isOn.TheInstance.invoke(this, null));
	}

	public BMTLBooleanInterface BMTL_hasNext() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Iterator_hasNext.TheInstance.invoke(this, null));
	}

	public BMTLOclAnyInterface BMTL_item() {
		return (BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(Iterator_item.TheInstance.invoke(this, null));
	}

	public BMTLVoidInterface BMTL_next() {
		return (BMTLVoidInterface)CommonFunctions.toBMTLDataType(Iterator_next.TheInstance.invoke(this, null));
	}

	public BMTLOclAnyInterface BMTL_nextItem() {
		return (BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(Iterator_next.TheInstance.invoke(this, null));
	}

}
