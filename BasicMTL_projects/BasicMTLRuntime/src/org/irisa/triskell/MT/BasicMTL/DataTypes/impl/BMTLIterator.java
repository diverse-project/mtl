/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
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
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface#getIteratorDelegate()
	 */
	public IteratorValueImpl getIteratorDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

}
