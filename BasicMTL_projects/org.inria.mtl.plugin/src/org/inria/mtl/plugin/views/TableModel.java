/*
* $Id: TableModel.java,v 1.1 2004-05-28 16:53:01 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TableModel
{
	public static final int LEVEL = 0, TIME = 1, LOGGERNAME = 2, NDC = 3, THREAD = 4, MESSAGE = 5, THROWABLE = 6, LOCATION = 7;

	private static final int[] COLUMNS = {LEVEL, TIME, LOGGERNAME, NDC, THREAD, MESSAGE, THROWABLE, LOCATION};
	
	private static final String[] NAMES = {"", "Time", "Logger", "NDC", "Thread", "Message", "Throwable", "Location"};

	public static final List PRIORITIES = new ArrayList(5);
	static
	{
		PRIORITIES.add(Level.DEBUG);
		PRIORITIES.add(Level.INFO);
		PRIORITIES.add(Level.WARN);
		PRIORITIES.add(Level.ERROR);
		PRIORITIES.add(Level.FATAL);
	}

	public final static int getColumnCount()
	{
		return COLUMNS.length;
	}

	public final static int getColumnId(int index)
	{
		return COLUMNS[index];
	}

	public final static String getColumnHeader(int index)
	{
		return NAMES[index];
	}
}