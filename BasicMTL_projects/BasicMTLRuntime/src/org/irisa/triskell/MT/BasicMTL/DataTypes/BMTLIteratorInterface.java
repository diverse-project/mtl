/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IteratorValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLIteratorInterface extends BMTLOclAnyInterface {
	IteratorValueImpl getIteratorDelegate();
	
	BMTLVoidInterface BMTL_start();
	
	BMTLBooleanInterface BMTL_isOff();
	
	BMTLBooleanInterface BMTL_isOn();
	
	BMTLBooleanInterface BMTL_hasNext();
	
	BMTLOclAnyInterface BMTL_item();
	
	BMTLVoidInterface BMTL_next();
	
	BMTLOclAnyInterface BMTL_nextItem();
}
