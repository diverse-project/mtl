/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_append;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_excluding;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_including;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_insertAt;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_prepend;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLSequence extends BMTLCollection implements BMTLSequenceInterface {
	
	public BMTLSequence() {
		this(new Value [0]);
	}
	
	public BMTLSequence(Value [] values) {
		this(new SequenceValueImpl(false, null, values));
	}

	public BMTLSequence(Value delegate) {
		super(delegate);
	}

	public BMTLSequenceInterface BMTL_append(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_append.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSequenceInterface BMTL_prepend(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_prepend.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSequenceInterface BMTL_insertAt(IntegerValue index, Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_insertAt.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(index), CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSequenceInterface BMTL_including(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSequenceInterface BMTL_excluding(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_excluding.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

}
