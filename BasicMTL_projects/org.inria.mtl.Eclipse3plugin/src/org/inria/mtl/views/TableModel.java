/*
* $Id: TableModel.java,v 1.3 2004-10-19 11:46:47 dvojtise Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

/**
 * Output table Model 
 */
public class TableModel
{
	public static final int LEVEL = 0, TIME = 1, LOGGERNAME = 2, /*NDC = 3, THREAD = 3,*/ MESSAGE = 3, /*THROWABLE = 5,*/ LOCATION = 4;

	private static final int[] COLUMNS = {LEVEL, TIME, LOGGERNAME, /*NDC, THREAD,*/ MESSAGE, /*THROWABLE,*/ LOCATION};
	
	private static final String[] NAMES = {"", "Time", "Logger", /*"NDC", "Thread",*/ "Message", /*"Throwable",*/ "Location"};

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