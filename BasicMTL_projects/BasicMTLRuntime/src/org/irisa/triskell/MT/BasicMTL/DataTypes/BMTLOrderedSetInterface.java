/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLOrderedSetInterface extends BMTLCollectionInterface {
	BMTLOrderedSetInterface BMTL_append (Value s);
	BMTLOrderedSetInterface BMTL_prepend (Value s);
	BMTLOrderedSetInterface BMTL_insertAt (IntegerValue index, Value s);
	BMTLOrderedSetInterface BMTL_including (Value s);
}
