/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLStringInterface
	extends StringValue, BMTLOclAnyInterface {
	StringValue getStringDelegate();
	
	BMTLIntegerInterface BMTL_size ();
	
	BMTLStringInterface BMTL_concat (BMTLStringInterface s);
	
	BMTLStringInterface BMTL_substring (BMTLIntegerInterface lower, BMTLIntegerInterface upper);
	
	BMTLIntegerInterface BMTL_toInteger ();
	
	BMTLRealInterface BMTL_toReal ();
}
