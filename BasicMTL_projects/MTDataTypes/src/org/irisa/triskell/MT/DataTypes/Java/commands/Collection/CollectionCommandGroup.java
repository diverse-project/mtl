/*
 * Created on May 22, 2003
 * $Id: CollectionCommandGroup.java,v 1.7 2004-02-16 17:01:53 dvojtise Exp $
 * @author : ffondeme 
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.DataTypes.Java.commands.Collection;

import java.util.Arrays;
import java.util.Hashtable;

//import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
//import org.irisa.triskell.MT.DataTypes.Java.RealValue;
//import org.irisa.triskell.MT.DataTypes.Java.Type;
//import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
//import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;

/**
 * Declaration of command callable from a MTL Collection object
 */
public class CollectionCommandGroup extends CommandGroupImpl {
	// keys are collection types (CollectionType) and values are collection command groups (CollectionCommandGroup) - of this exact type !	private static Hashtable defined
	private static final Hashtable collectionCommandGroups = new Hashtable();	
	public static CollectionCommandGroup getCollectionCommandGroup (CollectionType collectionType) {
		CollectionCommandGroup ret = (CollectionCommandGroup)collectionCommandGroups.get(collectionType);
		if (ret == null) {
			ret = new CollectionCommandGroup(collectionType);
			ret.addCommand(Collection_getNewIterator.TheInstance);
			ret.addCommand(Collection_isEmpty.TheInstance);
			ret.addCommand(Collection_size.TheInstance);
			ret.addCommand(Collection_at.TheInstance);
			ret.addCommand(Collection_includes.TheInstance);
			ret.addCommand(Collection_excludes.TheInstance);
			ret.addCommand(Collection_includesAll.TheInstance);
			ret.addCommand(Collection_excludesAll.TheInstance);
			collectionCommandGroups.put(collectionType, ret);
		}
		return ret;
	}
	
	protected final CollectionType type;
	
	protected CollectionCommandGroup(CollectionType collectionType) {
		super(collectionType, Arrays.asList(new CommandGroup [0]));
		this.type = collectionType;
	}
	
	public CollectionType getCollectionType () {
		return this.type;
	}
}
