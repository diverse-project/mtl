/*
 * Created on May 22, 2003
 * $Id: RepositoryAPICommandGroup.java,v 1.1 2004-10-28 15:10:11 jpthibau Exp $
 * @author : edrezen 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.RepositoryAPI;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * @author ffondeme
 *
 * Definition of command callable from a ModelElement object
 */
public class RepositoryAPICommandGroup extends CommandGroupImpl {
	public static final CommandGroup TheInstance;
	
	static {
		TheInstance = new RepositoryAPICommandGroup();
		
		TheInstance.addCommand (RepositoryAPI_oclUid.TheInstance);
		TheInstance.addCommand (RepositoryAPI_getType.TheInstance);		
	}
	
	private RepositoryAPICommandGroup() {
		super(RepositoryAPIType.TheInstance, Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}));
	}
}
