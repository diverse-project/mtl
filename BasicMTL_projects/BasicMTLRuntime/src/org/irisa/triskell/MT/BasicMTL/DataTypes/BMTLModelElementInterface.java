/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLModelElementInterface extends ModelElement, BMTLOclAnyInterface {
	ModelElement getModelElementDelegate();
	
	BMTLVoidInterface BMTL_delete();
	
	BMTLStringInterface BMTL_oclUid();
}
