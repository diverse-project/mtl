/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLSet extends BMTLCollection implements BMTLSetInterface {
	
	public BMTLSet() {
		this(new Value [0]);
	}
	
	public BMTLSet(Value [] collection) {
		this(new SetValueImpl(false, null, collection, false));
	}

	/**
	 * @param delegate
	 */
	public BMTLSet(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

}
