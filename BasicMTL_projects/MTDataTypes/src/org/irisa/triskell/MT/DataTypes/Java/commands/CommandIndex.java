/*
 * Created on May 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;


public class CommandIndex extends AbstractSet implements Serializable {
	protected ArrayList allCommands = new ArrayList();
	protected TreeMap map = new TreeMap();
	
	/**
	 * Changing this collection would also change the content of the
	 * index. The result may be null if none of the indexed commands
	 * has this name.
	 * @param name
	 * @return Collection
	 */
	public Collection getCommandsOfName (String name) {
		return (Collection)this.map.get(name);
	}
	
	public boolean addCommand (Command command) {
		if (this.addCommandToMap(command)) {
			this.addCommandToList(command);
			return true;
		} else
			return false;
	}
	
	protected boolean addCommandToMap (Command command) {
		String cn = command.getName();
		Collection ci = this.getCommandsOfName(cn);
		if (ci == null) {
			ci = new ArrayList();
			map.put(cn, ci);
		}
		if (ci.contains(command))
			return false;
		else {
			ci.add(command);
			return true;
		}
	}
	
	protected boolean addCommandToList (Command command) {
		return this.allCommands.add(command);
	}
	
	public boolean removeCommand (Command command) {
		if (this.removeCommandFromMap(command)) {
			this.removeCommandFromList(command);
			return true;
		} else
			return false;
	}
	
	protected boolean removeCommandFromMap (Command command) {
		String cn = command.getName();
		Collection ci = this.getCommandsOfName(cn);
		if (ci == null) {
			return false;
		} else if (ci.contains(command)) {
			if (ci.size() == 1)
				this.map.remove(command);
			else
				ci.remove(command);
			this.allCommands.remove(command);
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean removeCommandFromList (Command command) {
		return this.allCommands.remove(command);
	}
	
	public boolean add(Object o) {
		return this.addCommand((Command)o);
	}
	
	public boolean remove(Object o) {
		return this.removeCommand((Command)o);
	}

	public void clear() {
		this.map.clear();
		this.allCommands.clear();
	}

	public boolean contains(Object o) {
		Collection ci = this.getCommandsOfName(((Command)o).getName());
		return ci != null && ci.contains(o);
	}

	public Iterator iterator() {
		return new CommandIndexIterator();
	}

	public int size() {
		return this.allCommands.size();
	}

	public Object[] toArray() {
		return this.allCommands.toArray();
	}

	public Object[] toArray(Object[] a) {
		return this.allCommands.toArray(a);
	}
	
	protected class CommandIndexIterator implements Iterator {
		protected Iterator delegate = CommandIndex.this.allCommands.iterator();
		protected Command lastReturnedCommand = null;
		
		public boolean hasNext() {
			return this.delegate.hasNext();
		}

		public Command nextCommand() {
			return this.lastReturnedCommand = (Command)this.delegate.next();
		}

		public Object next() {
			return this.nextCommand();
		}

		public void remove() {
			this.delegate.remove();
			CommandIndex.this.removeCommandFromMap(this.lastReturnedCommand);
		}

	}
}