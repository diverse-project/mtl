/*
* $Id: ViewLabelProvider.java,v 1.3 2004-10-19 11:46:48 dvojtise Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views;

import java.text.SimpleDateFormat;

import org.inria.mtl.views.server.Entry;


import org.apache.log4j.Level;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
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
			//case TableModel.NDC :
			//	return getText(entry.getNDC());
			//case TableModel.THREAD :
			//	return getText(entry.getThreadName());
			case TableModel.MESSAGE :
				return getText(entry.getMessage());
			/*case TableModel.THROWABLE :
				return getText(entry.getThrowableStrRep());*/
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