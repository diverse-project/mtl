/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.RepositoryAPI;

import org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.PrimitiveType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;


public class RepositoryAPIType extends PrimitiveType {
	public static final Type TheInstance = new RepositoryAPIType();
	public static final String Name = "RepositoryAPI"; 
	
	private RepositoryAPIType () {
		super(Name, new Type[] {OclAnyType.TheInstance});
	}
	
	public boolean isKindOfInternal(Value v) {
		return v instanceof RepositoryAPIValue;
	}

}