/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
import java.util.*;

import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommandGroupImpl implements CommandGroup, Serializable {
    protected final Type owner;
	protected Collection parent;
	protected CommandIndex commands;
	
	public CommandGroupImpl (Type owner, Collection parents) {
		this.owner = owner;
		this.parent = parents == null ? Arrays.asList(new CommandGroupImpl [0]) : parents;
		this.commands = new CommandIndex();
	}
	
    public Collection getDirectCommands()
    {
		return this.commands;
    }
    
    public Collection getCommandsNamed (String name) {
    	return this.commands.getCommandsOfName(name);
    }
    
    public void addCommand (Command c) {
    	this.commands.add(c);
    }
    
    public Type getOwner() {
    	return this.owner;
    }
    
    public boolean checkInvoker (Value invoker) {
    	return this.getOwner() == null || invoker.isUndefined() || this.getOwner().isKindOf(invoker);
    }
    
    public Command getDirectInvokableCommand (
        String name,
        Value[] arguments,
        String[] discriminants) throws MultipleCommandException
    {
    	Collection commandsWithName = this.getCommandsNamed(name);
    	if (commandsWithName == null)
    		return null;
		Iterator it = commandsWithName.iterator();
		Command cmd = null, tmp;
		Collection mcdd = null;
		while (it.hasNext()) {
			tmp = (Command)it.next();
			if (tmp.isInvokable(arguments, discriminants)) {
				if (cmd == null)
					cmd = tmp;
				else {
					if (mcdd == null) {
						mcdd = new ArrayList();
						mcdd.add(cmd);
					}
					mcdd.add(tmp);
				}
			}
		}
		
		if (mcdd != null)
			throw new MultipleCommandException((Command[])mcdd.toArray(new Command [mcdd.size()]));
		
		return cmd;
    }
    
    public Command getInvokableCommand (
    	String [] qualifiedName,
        String name,
        Value[] arguments,
        String[] discriminants
    	) throws MultipleCommandException
    {	
    	Iterator it;
		Set levelSuperCommands = new HashSet();
		levelSuperCommands.add(this);
		java.util.Set alreadyExplored = new java.util.HashSet();
		CommandGroup explored, cg;
		Command ret = null, tmp;
		Collection mcdd = null;
		boolean qn = qualifiedName != null, qnFound = false, lookup;
		while (!levelSuperCommands.isEmpty()) {
			it = levelSuperCommands.iterator();
			while (it.hasNext()) {
				explored = (CommandGroup)it.next();
				qnFound = qn && Arrays.equals(explored.getOwner().getQualifiedName(), qualifiedName);
				lookup = (!qn) || qnFound;
				if (lookup) {
					tmp = explored.getDirectInvokableCommand(name, arguments, discriminants);
					if (tmp != null) {
						if (ret == null)
							ret = tmp;
						else {
							if (mcdd == null) {
								mcdd = new ArrayList();
								mcdd.add(ret);
							}
							mcdd.add(tmp);
						}
					}
					alreadyExplored.add(explored);
				}
				if (qnFound)
					return ret;
			}
			if (ret != null)
				if (mcdd == null) {
					return ret;
				} else
					throw new MultipleCommandException((Command[])mcdd.toArray(new Command [mcdd.size()]));
			
			it = levelSuperCommands.iterator();
			levelSuperCommands = new java.util.HashSet();
			while (it.hasNext()) {
				cg = (CommandGroup)it.next();
				levelSuperCommands.addAll(cg.getParents());
			}
			levelSuperCommands.removeAll(alreadyExplored);
		}
		return null;	
    }

    public Value invoke(
    	String [] scopeQualifiedName,
    	Value invoker,
        String name,
        Value[] arguments,
        String[] discriminants)
        throws UnknownCommandException, MultipleCommandException
    {
    	if (! this.checkInvoker(invoker))
    		throw new UnknownCommandException(invoker, name, arguments, discriminants, "the invoker is not of the right type");
    	Command toInvoke = this.getInvokableCommand(scopeQualifiedName, name, arguments, discriminants);
    	if (toInvoke == null)
			throw new UnknownCommandException(invoker, name, arguments, discriminants, null);
		else
			return toInvoke.invoke(invoker, arguments);
    }

	public Collection getParents() {
		return this.parent;
	}

	public String toString () {
		return this.getOwner().toString();
	}
}

	