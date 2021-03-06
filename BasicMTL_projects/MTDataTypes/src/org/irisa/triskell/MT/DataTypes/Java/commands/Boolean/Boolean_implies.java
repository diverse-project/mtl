/**
 * Created on May 27, 2003
 * $Id: Boolean_implies.java,v 1.2 2004-02-16 17:02:18 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Boolean;

//import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.AbstractCommand;
//import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;

/**
 * Definition of implies as a command for Boolean
 */
public class Boolean_implies extends AbstractCommand {
	public static Boolean_implies TheInstance = new Boolean_implies();

	protected Boolean_implies() {
		super("implies", new Type [] {BooleanType.TheInstance}, null);
	}
	
	protected Value invokeInternal(Value invoker, Value[] arguments) {
		return Boolean_or.TheInstance.invoke(Boolean_not.TheInstance.invoke(invoker, null), new Value [] {Boolean_and.TheInstance.invoke(invoker, new Value [] {arguments[0]})});
	}
}
