/*
 * Created on May 22, 2003
 * $Id: EnumCommandGroup.java,v 1.4 2004-02-16 17:02:16 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.enum;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * Declaration of callable command on Enum objects
 */
public class EnumCommandGroup extends CommandGroupImpl {
	
	public EnumCommandGroup(EnumType type) {
		super(type, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
		this.addCommand(enum_name.TheInstance);
	}

}