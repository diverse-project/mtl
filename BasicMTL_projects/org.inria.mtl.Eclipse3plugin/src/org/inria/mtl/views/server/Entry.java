/*
* $Id: Entry.java,v 1.1 2004-07-30 14:08:56 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.server;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author tcn
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Entry
{	
	/** the time of the event * */
	private Date time;
	/** the priority of the event * */
	private Level level;
	/** the category of the event * */
	private String loggerName;
	/** the NDC for the event * */
	private String ndc;
	/** the thread for the event * */
	private String threadName;
	/** the msg for the event * */
	private String message;
	/** the throwable details the event * */
	private String[] throwableStrRep;
	/** the location details for the event * */
	private String locationDetails;

	public Entry(long time, Level level, String loggerName, String ndc, String threadName, String message, String[] throwableStrRep,
			String locationDetails)
	{
		this.time = new Date(time);
		this.level = level;
		this.loggerName = loggerName;
		this.ndc = ndc;
		this.threadName = threadName;
		this.message = message;
		this.throwableStrRep = throwableStrRep;
		this.locationDetails = locationDetails;
	}


	/**
	 * Creates a new <code>EventDetails</code> instance.
	 * 
	 * @param aEvent
	 *                   a <code>LoggingEvent</code> value
	 */
	public Entry(LoggingEvent event)
	{		
		this.time = new Date (event.timeStamp);
		this.level = event.getLevel();
		this.loggerName = event.getLoggerName();
		this.ndc = event.getNDC();
		this.threadName = event.getThreadName();
		this.message = event.getMessage().toString();
		this.throwableStrRep = event.getThrowableStrRep();
		this.locationDetails = (event.getLocationInformation() == null)
						? null
						: event.getLocationInformation().fullInfo;
			System.out.println("Marker créé");

		
	}

	/**
	 * @return Returns the categoryName.
	 */
	public String getLoggerName()
	{
		return this.loggerName;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails()
	{
		return this.locationDetails;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * @return Returns the ndc.
	 */
	public String getNDC()
	{
		return this.ndc;
	}
	/**
	 * @return Returns the priority.
	 */
	public Level getLevel()
	{
		return this.level;
	}
	/**
	 * @return Returns the threadName.
	 */
	public String getThreadName()
	{
		return this.threadName;
	}
	/**
	 * @return Returns the throwableStrRep.
	 */
	public String[] getThrowableStrRep()
	{
		return this.throwableStrRep;
	}
	/**
	 * @return Returns the timeStamp.
	 */
	public Date getTime()
	{
		return this.time;
	}
	


}