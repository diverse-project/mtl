/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.TypeValue;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLOclTypeInterface extends TypeValue, BMTLOclAnyInterface {
	TypeValue getOclTypeDelegate();
	
	BMTLSetInterface BMTL_allInstances();
	
	BMTLOrderedSetInterface BMTL_oclQualifiedName();
	
	BMTLStringInterface BMTL_oclName();
	
}
