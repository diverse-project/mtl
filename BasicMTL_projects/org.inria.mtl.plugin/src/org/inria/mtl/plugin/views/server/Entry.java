/*
* $Id: Entry.java,v 1.1 2004-05-28 16:52:33 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.plugin.views.server;
import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.ui.views.tasklist.TaskList;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.*;
import org.inria.mtl.plugin.core.MTLCore;
import org.inria.mtl.plugin.MTLPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.views.tasklist.TaskList;

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
		//Create a task
		try{
			createMarkers(event);
			System.out.println("Marker créé");
//			
		}catch (CoreException e){
			System.out.println("Marker non créé"+e.getMessage());
			MTLPlugin.log(e);
		}
		
		
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
	
/**
 * Create markers according to the compiler output
 */
	public void createMarkers(LoggingEvent entry) throws CoreException {
		
	String Message=entry.getMessage().toString();
	//System.out.println("Message initial:"+Message);
	int AntLrTest=Message.indexOf("ANTLRException");
	//System.out.println("Entrée Create Markers :"+AntLrTest);
	//Antlr Exception Case
	if (Message.indexOf("ANTLRException")==0){
		
		String messageAll = Message.substring(Message.indexOf(" on ") + 4, Message.length() ); //$NON-NLS-1$
		//System.out.println("Message All:"+messageAll);
		String messageFile = messageAll.substring(0, messageAll.indexOf(",")); //$NON-NLS-1$
		System.out.println("Message File:"+messageFile);
		String messageDelta = messageAll.substring(messageAll.indexOf(", ") + 2, messageAll.length()); //$NON-NLS-1$
		//System.out.println("Message Delta:"+messageDelta);
		//System.out.println("Message line:"+messageAll.indexOf(" line ")+"Test :"+messageAll.indexOf(":",messageAll.indexOf(" line ")));
		String messageLine = messageDelta.substring(messageDelta.indexOf("line ")+5 ,messageDelta.indexOf(":")); //$NON-NLS-1$
		//System.out.println("Message Line:"+messageLine);
		String messageDesc = messageDelta.substring(messageDelta.indexOf(":",messageDelta.indexOf(":")+1)+1, messageDelta.length()); //$NON-NLS-1$
		//System.out.println("Message Desc:"+messageDesc);
		

		Hashtable attributes = new Hashtable();
		MarkerUtilities.setMessage(attributes, messageDesc);
		attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		
		try{
			int lineNumber =
				Integer.parseInt(messageLine);	//$NON-NLS-1$
				MarkerUtilities.setLineNumber(attributes, lineNumber);
		}catch (Exception e){
			System.out.println("Error Line number :");
			
		}
		
		String N=messageFile.substring(messageFile.indexOf(MTLCore.getProject().getName())+MTLCore.getProject().getName().length()+1);
		//System.out.println("File name :"+N);
		IFile currFile=MTLCore.getProject().getFile(N);
		currFile.deleteMarkers(IMarker.PROBLEM, false, 0);
		MarkerUtilities.createMarker(currFile, attributes, IMarker.PROBLEM);
		
		//And refresh the compilation unit folder
		currFile.getParent().refreshLocal(IResource.DEPTH_ONE, null);
		
		}
	}


}