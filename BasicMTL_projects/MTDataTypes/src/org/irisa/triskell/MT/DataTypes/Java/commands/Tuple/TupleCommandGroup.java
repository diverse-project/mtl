/*
 * $Id: TupleCommandGroup.java,v 1.3 2004-02-16 17:02:05 dvojtise Exp $
 * @author : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Tuple;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * Declaration of callable commands for Tuple objects
 */
public class TupleCommandGroup extends CommandGroupImpl {
	
	public TupleCommandGroup(TupleType type) {
		super(type, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}

}