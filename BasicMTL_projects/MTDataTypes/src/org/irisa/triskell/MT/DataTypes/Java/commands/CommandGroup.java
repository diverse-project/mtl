/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import java.util.Collection;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface CommandGroup {
	Type getOwner ();
	Collection getParents ();
	void addCommand (Command c);
	Collection getDirectCommands();
    boolean checkInvoker (Value invoker);
	Command getDirectInvokableCommand(
		String name,
		Value[] arguments,
		String[] discriminants)
		throws MultipleCommandException;
	Command getInvokableCommand(
    	String [] qualifiedName,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws MultipleCommandException;
	Value invoke(
    	String [] scopeQualifiedName,
		Value invoker,
		String name,
		Value[] arguments,
		String[] discriminants)
		throws UnknownCommandException, MultipleCommandException;
}