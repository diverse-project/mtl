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
public interface BMTLSequenceInterface extends BMTLCollectionInterface {
	BMTLSequenceInterface BMTL_append (Value s);
	BMTLSequenceInterface BMTL_prepend (Value s);
	BMTLSequenceInterface BMTL_insertAt (IntegerValue index, Value s);
	BMTLSequenceInterface BMTL_including (Value s);
}
