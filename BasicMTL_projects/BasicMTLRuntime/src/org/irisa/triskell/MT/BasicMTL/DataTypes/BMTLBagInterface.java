/*
 * Created on 19 ao�t 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLBagInterface extends BMTLCollectionInterface {
	BMTLBagInterface BMTL_including (Value s);
	BMTLBagInterface BMTL_excluding (Value s);
}
