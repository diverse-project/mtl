/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclType;

import java.util.Collection;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;


public class OclTypeType extends PrimitiveType {
	public static final Type TheInstance = new OclTypeType();
	public static final String Name = "OclType";
	
	private OclTypeType () {
		super(Name, new Type [] {OclAnyType.TheInstance});
	}

	public boolean isKindOfInternal(Value c) {
		return c instanceof TypeValue;
	}

}