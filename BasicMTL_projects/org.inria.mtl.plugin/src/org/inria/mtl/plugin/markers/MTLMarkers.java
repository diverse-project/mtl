/*
* $Id: MTLMarkers.java,v 1.2 2004-06-03 13:08:53 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.markers;

import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.texteditor.MarkerUtilities;

/**
 * The superclass for our MTL Markers.
 */
public abstract class MTLMarkers {
  // strings for external parser call
  private static final String PARSE_ERROR_STRING = "Parse error"; //$NON-NLS-1$
  private static final String PARSE_WARNING_STRING = "Warning"; //$NON-NLS-1$
  public static final int ERROR = 2;
  public static final int WARNING = 1;
  public static final int INFO = 0;
  public static final int TASK = 3;
  
  protected static IFile fileToParse;

   /**
   * This will change the file to parse.
   * @param fileToParse the file that should be parsed
   */
  public abstract void setFileToParse(IFile fileToParse);
  /**
   * This will set a marker.
   * @param file the file that generated the marker
   * @param message the message
   * @param charStart the starting character
   * @param charEnd the end character
   * @param errorLevel the error level,
   * @throws CoreException an exception throwed by the MarkerUtilities
   */
  public static void setMarker(
	  final IFile file,
	  final String message,
	  final int charStart,
	  final int charEnd,
	  final int errorLevel)
	  throws CoreException {
	if (file != null) {
	  final Hashtable attributes = new Hashtable();
	  MarkerUtilities.setMessage(attributes, message);
	  switch (errorLevel) {
		case ERROR:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		  break;
		case WARNING:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
		  break;
		case INFO:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
		  break;
		case TASK:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.TASK));
		  break;
	  }
	  MarkerUtilities.setCharStart(attributes, charStart);
	  MarkerUtilities.setCharEnd(attributes, charEnd);
	  MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);
	}
  }

  /**
   * This will set a marker.
   * @param file the file that generated the marker
   * @param message the message
   * @param line the line number
   * @param errorLevel the error level 
   * @throws CoreException an exception throwed by the MarkerUtilities
   */
  public static void setMarker(final IFile file,
							   final String message,
							   final int line,
							   final int errorLevel,
							   final String location)
	  throws CoreException {
	if (file != null) {
	  String markerKind = IMarker.PROBLEM;
	  final Hashtable attributes = new Hashtable();
	  MarkerUtilities.setMessage(attributes, message);
	  switch (errorLevel) {
		case ERROR:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		  break;
		case WARNING:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
		  break;
		case INFO:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
		  break;
		case TASK:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
		  markerKind = IMarker.TASK;
		  break;
	  }
	  attributes.put(IMarker.LOCATION, location);
	  MarkerUtilities.setLineNumber(attributes, line);
	  MarkerUtilities.createMarker(file, attributes, markerKind);
	}
  }

  /**
   * This will set a marker.
   * @param message the message
   * @param charStart the starting character
   * @param charEnd the end character
   * @param errorLevel the error level 
   * @throws CoreException an exception throwed by the MarkerUtilities
   */
  public static void setMarker(final String message,
							   final int charStart,
							   final int charEnd,
							   final int errorLevel,
							   final String location)
	  throws CoreException {
	if (fileToParse != null) {
	  setMarker(fileToParse, message, charStart, charEnd, errorLevel, location);
	}
  }

  /**
   * This will set a marker.
   * @param file the file that generated the marker
   * @param message the message
   * @param charStart the starting character
   * @param charEnd the end character
   * @param errorLevel the error level 
   * @param location the location of the error
   * @throws CoreException an exception throwed by the MarkerUtilities
   */
  public static void setMarker(final IFile file,
							   final String message,
							   final int charStart,
							   final int charEnd,
							   final int errorLevel,
							   final String location)
	  throws CoreException {
	if (file != null) {
	  final Hashtable attributes = new Hashtable();
	  MarkerUtilities.setMessage(attributes, message);
	  switch (errorLevel) {
		case ERROR:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		  break;
		case WARNING:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
		  break;
		case INFO:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
		  break;
		case TASK:
		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.TASK));
		  break;
	  }
	  attributes.put(IMarker.LOCATION, location);
	  MarkerUtilities.setCharStart(attributes, charStart);
	  MarkerUtilities.setCharEnd(attributes, charEnd);
	  MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);
	}
  }
}
