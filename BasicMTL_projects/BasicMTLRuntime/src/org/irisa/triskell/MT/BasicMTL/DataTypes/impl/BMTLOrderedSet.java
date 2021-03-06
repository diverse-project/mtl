/*
 * Created on 19 ao�t 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSet_append;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSet_excluding;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSet_including;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSet_insertAt;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSet_prepend;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLOrderedSet
	extends BMTLCollection
	implements BMTLOrderedSetInterface {
	
	public BMTLOrderedSet() {
		this(new Value [0]);
	}
	
	public BMTLOrderedSet(Value [] values) {
		this(new OrderedSetValueImpl(false, null, values));
	}

	public BMTLOrderedSet(Value delegate) {
		super(delegate);
	}

	public BMTLOrderedSetInterface BMTL_append(Value s) {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OrderedSet_append.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLOrderedSetInterface BMTL_prepend(Value s) {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OrderedSet_prepend.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLOrderedSetInterface BMTL_insertAt(IntegerValue index, Value s) {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OrderedSet_insertAt.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(index), CommonFunctions.toMTDataType(s)}));
	}

	public BMTLOrderedSetInterface BMTL_including(Value s) {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OrderedSet_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLOrderedSetInterface BMTL_excluding(Value s) {
		return (BMTLOrderedSetInterface)CommonFunctions.toBMTLDataType(OrderedSet_excluding.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

}
