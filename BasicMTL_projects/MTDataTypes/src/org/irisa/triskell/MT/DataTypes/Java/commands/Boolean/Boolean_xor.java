/*
 * Created on May 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_equals;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Boolean_xor extends AbstractCommand {
	public static Boolean_xor TheInstance = new Boolean_xor();

	protected Boolean_xor() {
		super("xor", new Type [] {BooleanType.TheInstance}, null);
	}
	
	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_and.TheInstance.invoke(Boolean_or.TheInstance.invoke(invoker, new Value [] {arguments[0]}), new Value [] {Boolean_not.TheInstance.invoke(OclAny_equals.TheInstance.invoke(invoker, new Value [] {arguments[0]}), null)});
	}
}