/* $Id: BMTLSetInterface.java,v 1.6 2004-05-19 22:21:21 ffondeme Exp $
 * Created on 19 août 2003
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * Interface defining the methods available on Standard::Set objcets in BasicMTL
 */
public interface BMTLSetInterface extends BMTLCollectionInterface {
	BMTLSetInterface BMTL_union (BMTLSetInterface s);
	BMTLSetInterface BMTL_intersection (BMTLSetInterface s);
	BMTLSetInterface BMTL__2d (BMTLSetInterface s);
	BMTLSetInterface BMTL_including (Value s);
	BMTLSetInterface BMTL_excluding (Value s);
	BMTLSetInterface BMTL_symmetricDifference (BMTLSetInterface s);	
}