/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLCollection
	extends BMTLOclAny
	implements BMTLCollectionInterface {

	/**
	 * @param delegate
	 */
	public BMTLCollection(Value delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface#getCollectionDelegate()
	 */
	public CollectionValue getCollectionDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.CollectionValue#getTheCollection()
	 */
	public Value[] getTheCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.CollectionValue#getKind()
	 */
	public CollectionKind getKind() {
		// TODO Auto-generated method stub
		return null;
	}

}
