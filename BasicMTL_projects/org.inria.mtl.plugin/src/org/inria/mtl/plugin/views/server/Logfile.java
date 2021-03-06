/*
* $Id: Logfile.java,v 1.1 2004-05-28 16:52:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/

package org.inria.mtl.plugin.views.server;

import java.util.List;
import java.util.Vector;

import org.inria.mtl.plugin.views.controller.Controller;

/**
 * @author tcn
 * 
 * The logfile actually holds all the logging events it received during
 * lifetime. It could easily be replaced by persistence storage.
 */
public class Logfile
{
	// synchronized
	private List logfile = new Vector(1000, 100);
	
	public Logfile()
	{
		Controller.getInstance().acquaint(this);
	}

	public void clear()
	{
		logfile.clear();
	}

	public Object[] toArray()
	{
		return logfile.toArray();
	}

	public void add(Entry entry)
	{
		logfile.add(entry);
	}
}