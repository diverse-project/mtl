/*
 * Created on 28 oct. 2004
 *
 */
package org.inria.mtl.views.projectexplorer.model;

import java.util.Iterator;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;

/**
 * @author edrezen
 *
 */
public class Container extends java.util.Vector 
{
	/** */
	public synchronized boolean add(Object o) 
	{
		if (o instanceof Library)
		{
			Library toRemove = null;
			
			Library lib = (Library)o;
			for (Iterator it=this.iterator(); it.hasNext() && toRemove==null; )
			{
				Library item = (Library) it.next();
				
				if (lib.getName().equals(item.getName()))
				{
					toRemove = item;
				}
			}
			
			if (toRemove != null)
			{
				this.remove (toRemove);
			}
		}
		
		return super.add (o);
	}
	

	/** */
	public void removeFromName (String name) 
	{
		for (Iterator it=this.iterator(); it.hasNext(); )
		{
			Library item = (Library) it.next();
			
			if (item.getName().equals(name))
			{
				this.remove (item);
				return;
			}
		}
	}
}
