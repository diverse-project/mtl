/*
 * Created on May 22, 2003
 * $Id: IteratorCommandGroup.java,v 1.3 2004-02-16 17:02:04 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Iterator;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * Declaration of commands callable from an Iterator object
 */
public class IteratorCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new IteratorCommandGroup();
		TheInstance.addCommand(Iterator_start.TheInstance);
		TheInstance.addCommand(Iterator_isOff.TheInstance);
		TheInstance.addCommand(Iterator_isOn.TheInstance);
		TheInstance.addCommand(Iterator_hasNext.TheInstance);
		TheInstance.addCommand(Iterator_item.TheInstance);
		TheInstance.addCommand(Iterator_next.TheInstance);
		TheInstance.addCommand(Iterator_nextItem.TheInstance);
	}
	
	private IteratorCommandGroup() {
		super(IteratorType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
