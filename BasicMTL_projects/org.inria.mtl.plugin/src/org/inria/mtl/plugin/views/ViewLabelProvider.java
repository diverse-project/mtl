/*
* $Id: ViewLabelProvider.java,v 1.1 2004-05-28 16:53:02 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views;

import java.text.SimpleDateFormat;

import org.inria.mtl.plugin.views.server.Entry;


import org.apache.log4j.Level;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ViewLabelProvider extends LabelProvider implements ITableLabelProvider
{
	private final static SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss.S");

	public String getColumnText(Object obj, int index)
	{
		Entry entry = (Entry) obj;

		switch (index)
		{
			case TableModel.LEVEL :
				return getText(entry.getLevel());
			case TableModel.TIME :
				return getText(SDF.format(entry.getTime()));
			case TableModel.LOGGERNAME :
				return getText(entry.getLoggerName());
			case TableModel.NDC :
				return getText(entry.getNDC());
			case TableModel.THREAD :
				return getText(entry.getThreadName());
			case TableModel.MESSAGE :
				return getText(entry.getMessage());
			case TableModel.THROWABLE :
				return getText(entry.getThrowableStrRep());
			case TableModel.LOCATION :
				return getText(entry.getLocationDetails());
		}
		
		return "";
	}

	public Image getColumnImage(Object obj, int index)
	{
		Entry entry = (Entry) obj;

		switch (index)
		{
			case TableModel.LEVEL :

				switch (entry.getLevel().toInt())
				{
					case Level.DEBUG_INT :
						return Utility.getImage("debug");

					case Level.INFO_INT :
						return Utility.getImage("info");

					case Level.WARN_INT :
						return Utility.getImage("warn");

					case Level.ERROR_INT :
						return Utility.getImage("error");

					case Level.FATAL_INT :
						return Utility.getImage("fatal");
				}
				break;
		}

		return null;
	}
}