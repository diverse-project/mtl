/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
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

	/**
	 * @param delegate
	 */
	public BMTLOrderedSet(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

}
