/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLCollectionInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIntegerInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_excludes;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_excludesAll;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_getNewIterator;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_includes;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_at;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_includesAll;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_isEmpty;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.Collection_size;

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
		return (BMTLIteratorInterface)CommonFunctions.toBMTLDataType(Collection_getNewIterator.TheInstance.invoke(this.getCollectionDelegate(), null));
	}

	public BMTLOclAnyInterface BMTL_at(IntegerValue  position) {
			return (BMTLOclAnyInterface)CommonFunctions.toBMTLDataType(Collection_at.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(position)}));
		}
		
	public BMTLBooleanInterface BMTL_isEmpty() {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Collection_isEmpty.TheInstance.invoke(this.getCollectionDelegate(), null));
	}
	
	public BMTLIntegerInterface BMTL_size() {
		return (BMTLIntegerInterface)CommonFunctions.toBMTLDataType(Collection_size.TheInstance.invoke(this.getCollectionDelegate(), null));
	}
	
	public BMTLBooleanInterface BMTL_includes(Value v) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Collection_includes.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(v)}));
	}
	
	public BMTLBooleanInterface BMTL_excludes(Value v) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Collection_excludes.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(v)}));
	}
	
	public BMTLBooleanInterface BMTL_includesAll(CollectionValue v) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Collection_includesAll.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(v)}));
	}
	
	public BMTLBooleanInterface BMTL_excludesAll(CollectionValue v) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(Collection_excludesAll.TheInstance.invoke(this.getCollectionDelegate(), new Value [] {CommonFunctions.toMTDataType(v)}));
	}


}
