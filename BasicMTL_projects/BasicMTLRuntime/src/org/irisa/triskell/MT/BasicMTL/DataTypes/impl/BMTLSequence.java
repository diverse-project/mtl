/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_append;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.Sequence_including;
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

	/**
	 * @param delegate
	 */
	public BMTLSequence(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	public BMTLSequenceInterface BMTL_append(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_append.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

	public BMTLSequenceInterface BMTL_including(Value s) {
		return (BMTLSequenceInterface)CommonFunctions.toBMTLDataType(Sequence_including.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(s)}));
	}

}
