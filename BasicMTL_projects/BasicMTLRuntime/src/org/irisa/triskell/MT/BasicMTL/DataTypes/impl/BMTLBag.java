/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLBag extends BMTLCollection implements BMTLBagInterface {
	
	public BMTLBag() {
		this(new Value [0]);
	}
	
	public BMTLBag(Value [] values) {
		this(new BagValueImpl(false, null, values));
	}

	/**
	 * @param delegate
	 */
	public BMTLBag(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

}
