/*
* $Id: MTLMarkers.java,v 1.2 2004-08-26 12:41:04 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.markers;

import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.inria.mtl.MTLPlugin;

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
  
  public static boolean cleanMarkers=true;
  
  /**
   * Java model problem marker type (value <code>"org.eclipse.jdt.core.problem"</code>).
   * This can be used to recognize those markers in the workspace that flag problems 
   * detected by the Java tooling during compilation.
   */
  public static final String JAVA_MODEL_PROBLEM_MARKER = MTLPlugin.PLUGIN_ID + ".problem"; //$NON-NLS-1$


  /**
   * Java model transient problem marker type (value <code>"org.eclipse.jdt.core.transient_problem"</code>).
   * This can be used to recognize those markers in the workspace that flag transient
   * problems detected by the Java tooling (such as a problem
   * detected by the outliner, or a problem detected during a code completion)
   */
  public static final String TRANSIENT_PROBLEM = MTLPlugin.PLUGIN_ID + ".transient_problem"; //$NON-NLS-1$

  /**
   * Java model task marker type (value <code>"org.eclipse.jdt.core.task"</code>).
   * This can be used to recognize task markers in the workspace that correspond to tasks
   * specified in Java source comments and detected during compilation (e.g. 'TO-DO: ...').
   * Tasks are identified by a task tag, which can be customized through <code>JavaCore</code>
   * option <code>"org.eclipse.jdt.core.compiler.taskTag"</code>.
   * @since 2.1
   */
  public static final String TASK_MARKER = MTLPlugin.PLUGIN_ID + ".task"; //$NON-NLS-1$
  
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
