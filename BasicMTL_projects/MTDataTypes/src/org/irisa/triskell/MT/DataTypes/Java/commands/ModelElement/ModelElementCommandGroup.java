/*
 * Created on May 22, 2003
 * $Id: ModelElementCommandGroup.java,v 1.4 2004-02-16 17:02:17 dvojtise Exp $
 * @author : edrezen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * Definition of command callable from a ModelElement object
 */
public class ModelElementCommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new ModelElementCommandGroup();
		
		TheInstance.addCommand (ModelElement_delete.TheInstance);
		TheInstance.addCommand (ModelElement_oclUid.TheInstance);
		TheInstance.addCommand (ModelElement_getType.TheInstance);		
	}
	
	private ModelElementCommandGroup() {
		super(ModelElementType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
