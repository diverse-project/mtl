/*
* $Id: Sorter.java,v 1.3 2004-10-19 11:46:47 dvojtise Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views;
import org.inria.mtl.views.server.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
  * Sorter for the TableViewer that displays items 
 */
public class Sorter extends ViewerSorter
{
	// Criteria that the instance uses
	private int criteria;
	private boolean ascending = true;


	/**
	 * Creates a resource sorter that will use the given sort criteria.
	 * 
	 * @param criteria
	 *                   the sort criterion to use: one of <code>NAME</code> or
	 *                   <code>TYPE</code>
	 */
	public Sorter(int criteria, boolean ascending)
	{
		super();
		this.criteria = criteria;
		this.ascending = ascending;
	}

	public Sorter(int criteria)
	{
		this(criteria, false);
	}

	/*
	 * (non-Javadoc) Method declared on ViewerSorter.
	 */
	public int compare(Viewer viewer, Object o1, Object o2)
	{
		Entry a = (Entry) o1;
		Entry b = (Entry) o2;

		switch (criteria)
		{
			case TableModel.LEVEL :
				return compareLevel(a, b);
			case TableModel.TIME :
				return compareTime(a, b);
			case TableModel.LOGGERNAME :
				return compareLoggerName(a, b);
			//case TableModel.NDC :
			//	return compareNDC(a, b);
			//case TableModel.THREAD :
			//	return compareThreadName(a, b);
			case TableModel.MESSAGE :
				return compareMessage(a, b);
			/*case TableModel.THROWABLE :
				return compareThrowableStrRep(a, b);*/
			case TableModel.LOCATION :
				return compareLocationDetails(a, b);
		}

		return 0;
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareLocationDetails(Entry a, Entry b)
	{
		return flip(collator.compare(a.getLocationDetails(), b.getLocationDetails()));
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareThrowableStrRep(Entry a, Entry b)
	{
		return flip(collator.compare(a.getThrowableStrRep(), b.getThrowableStrRep()));
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareThreadName(Entry a, Entry b)
	{
		return flip(collator.compare(a.getThreadName(), b.getThreadName()));
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareNDC(Entry a, Entry b)
	{
		return flip(collator.compare(a.getNDC(), b.getNDC()));
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareLoggerName(Entry a, Entry b)
	{
		return flip(collator.compare(a.getLoggerName(), b.getLoggerName()));
	}



	/**
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareLevel(Entry a, Entry b)
	{
		int one = TableModel.PRIORITIES.indexOf(a.getLevel());
		int two = TableModel.PRIORITIES.indexOf(b.getLevel());
		
		if (one == -1 || two == -1) return 0;
		
		int d = one - two;  
		int r = d < 0 ? -1 : (d > 0) ? 1 : 0;
		
		return flip(r);
	}

	/**
	 * Returns a number reflecting the collation order of the given time stamps
	 * based on the time passed.
	 * 
	 * @param a
	 * @param b
	 * @return a negative number if the first element is less than the second
	 *              element; the value <code>0</code> if the first element is
	 *              equal to the second element; and a positive number if the first
	 *              element is greater than the second element
	 */
	protected int compareTime(Entry a, Entry b)
	{
		if (a.getTime().equals(b.getTime())) return 0; 
		
		int r = a.getTime().before(b.getTime()) ? -1 : 1;	
		return flip(r);
	}

	/**
	 *  
	 */
	private int flip(int value)
	{
		return ascending ? -value : value;
	}

	/**
	 * Returns a number reflecting the collation order of the given entries
	 * based on the message.
	 * 
	 * @param task1
	 *                   the first task element to be ordered
	 * @param resource2
	 *                   the second task element to be ordered
	 * @return a negative number if the first element is less than the second
	 *              element; the value <code>0</code> if the first element is
	 *              equal to the second element; and a positive number if the first
	 *              element is greater than the second element
	 */
	protected int compareMessage(Entry a, Entry b)
	{
		return flip(collator.compare(a.getMessage(), b.getMessage()));
	}

	/**
	 * Returns the sort criteria of this this sorter.
	 * 
	 * @return the sort criterion
	 */
	public int getCriteria()
	{
		return criteria;
	}
}