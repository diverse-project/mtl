/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_getNewIterator;

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
	}

	public CollectionValue getCollectionDelegate() {
		return (CollectionValue)this.getDelegate();
	}

	public Value[] getTheCollection() {
		return this.getCollectionDelegate().getTheCollection();
	}

	public CollectionKind getKind() {
		return this.getCollectionDelegate().getKind();
	}

	public BMTLIteratorInterface BMTL_getNewIterator() {
		return (BMTLIteratorInterface)CommonFunctions.toBMTLDataType(Collection_getNewIterator.TheInstance.invoke(this, null));
	}

}
