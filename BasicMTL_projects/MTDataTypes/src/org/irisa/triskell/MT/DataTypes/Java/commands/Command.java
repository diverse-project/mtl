/**
 * $Id: Command.java,v 1.2 2004-02-16 17:01:51 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

/**
 * Interface for invokable commands on DataTypes developped for MTL  
 */
public interface Command 
{

     String getName();

	/**
	 * @param arguments null means no arguments
	 * @param discriminants not tested if null
	 * @return boolean
	 */
     boolean isInvokable(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants);

     org.irisa.triskell.MT.DataTypes.Java.Value invoke(
        org.irisa.triskell.MT.DataTypes.Java.Value invoker,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments);

     String toString ();       
}
