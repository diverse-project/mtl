/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLSetInterface extends BMTLCollectionInterface {
	BMTLSetInterface BMTL_union (BMTLSetInterface s);
	BMTLSetInterface BMTL_intersection (BMTLSetInterface s);
	BMTLSetInterface BMTL_including (BMTLOclAnyInterface s);
}