/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLCollectionInterface
	extends CollectionValue, BMTLOclAnyInterface {
	CollectionValue getCollectionDelegate();
	
	BMTLIteratorInterface BMTL_getNewIterator();
	BMTLOclAnyInterface BMTL_at(IntegerValue position);
	BMTLBooleanInterface BMTL_isEmpty();
	BMTLBooleanInterface BMTL_notEmpty();
	BMTLIntegerInterface BMTL_size();
	BMTLBooleanInterface BMTL_includes(Value v);
	BMTLBooleanInterface BMTL_excludes(Value v);
	BMTLBooleanInterface BMTL_includesAll(CollectionValue v);
	BMTLBooleanInterface BMTL_excludesAll(CollectionValue v);
}
