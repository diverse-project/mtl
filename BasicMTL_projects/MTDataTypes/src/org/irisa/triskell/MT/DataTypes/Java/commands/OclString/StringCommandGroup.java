/*
 * $Id: StringCommandGroup.java,v 1.5 2004-07-08 07:42:04 edrezen Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.OclString;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 * List of commands accepted by the OclString
 */
public class StringCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new StringCommandGroup();
		TheInstance.addCommand(String_concat.TheInstance);
		TheInstance.addCommand(String_size.TheInstance);
		TheInstance.addCommand(String_substring.TheInstance);
		TheInstance.addCommand(String_toReal.TheInstance);
		TheInstance.addCommand(String_toInteger.TheInstance);
		TheInstance.addCommand(String_toOut.TheInstance);
		TheInstance.addCommand(String_toErr.TheInstance);
		TheInstance.addCommand(String_add.TheInstance);
		TheInstance.addCommand(String_toLowerCase.TheInstance);
		TheInstance.addCommand(String_toUpperCase.TheInstance);
	}
	
	private StringCommandGroup() {
		super(StringType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
