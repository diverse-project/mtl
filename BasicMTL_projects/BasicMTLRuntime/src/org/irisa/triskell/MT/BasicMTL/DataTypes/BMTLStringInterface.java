/*
 * $Id: BMTLStringInterface.java,v 1.3 2004-07-08 07:39:44 edrezen Exp $
 * Created on 19 août 2003
 *
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.DataTypes.Java.StringValue;

/**
 * @author ffondeme
 * BasicMTL implementation of the datatypes
 */
public interface BMTLStringInterface
	extends StringValue, BMTLOclAnyInterface {
	StringValue getStringDelegate();
	
	BMTLIntegerInterface BMTL_size ();
	
	BMTLStringInterface BMTL_concat (BMTLStringInterface s);
	
	BMTLStringInterface BMTL_substring (BMTLIntegerInterface lower, BMTLIntegerInterface upper);
	
	BMTLIntegerInterface BMTL_toInteger ();
	
	BMTLRealInterface BMTL_toReal ();
	
	BMTLVoidInterface BMTL_toOut();
	BMTLVoidInterface BMTL_toErr();
	
	BMTLStringInterface BMTL__2b (BMTLStringInterface s);
	
	BMTLStringInterface BMTL_toLowerCase ();
	BMTLStringInterface BMTL_toUpperCase ();
	
}
