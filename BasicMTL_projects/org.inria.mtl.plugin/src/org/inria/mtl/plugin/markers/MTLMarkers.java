package org.inria.mtl.plugin.markers;

import java.text.MessageFormat;
import java.util.Hashtable;

//import net.sourceforge.phpdt.internal.compiler.parser.PHPOutlineInfo;
//import net.sourceforge.phpdt.internal.ui.util.StringUtil;
//import net.sourceforge.phpeclipse.PHPeclipsePlugin;
//import net.sourceforge.phpeclipse.actions.PHPStartApacheAction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;
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
   * Call the php parse command ( php -l -f &lt;filename&gt; )
   * and create markers according to the external parser output.
   * @param file the file that will be parsed
   */
//  public static void phpExternalParse(final IFile file) {
//	//IFile file = (IFile) resource;
//	//  final IPath path = file.getFullPath();
//	final IPreferenceStore store = PHPeclipsePlugin.getDefault().getPreferenceStore();
//	final String filename = file.getLocation().toString();
//
//	final String[] arguments = {filename};
//	final MessageFormat form = new MessageFormat(store.getString(PHPeclipsePlugin.EXTERNAL_PARSER_PREF));
//	final String command = form.format(arguments);
//
//	final String parserResult = PHPStartApacheAction.getParserOutput(command, "External parser: ");
//
//	try {
//	  // parse the buffer to find the errors and warnings
//	  createMarkers(parserResult, file);
//	} catch (CoreException e) {
//	}
//  }

  /**
   * Create markers according to the external parser output.
   * @param output the external parser output
   * @param file the file that was parsed.
   */
//  protected static void createMarkers(final String output, final IFile file) throws CoreException {
//	// delete all markers
//	file.deleteMarkers(IMarker.PROBLEM, false, 0);
//
//	int indx = 0;
//	int brIndx;
//	boolean flag = true;
//	while ((brIndx = output.indexOf("<br />", indx)) != -1) {
//	  // newer php error output (tested with 4.2.3)
//	  scanLine(output, file, indx, brIndx);
//	  indx = brIndx + 6;
//	  flag = false;
//	}
//	if (flag) {
//	  while ((brIndx = output.indexOf("<br>", indx)) != -1) {
//		// older php error output (tested with 4.2.3)
//		scanLine(output, file, indx, brIndx);
//		indx = brIndx + 4;
//	  }
//	}
//  }

//  private static void scanLine(final String output, final IFile file, final int indx, final int brIndx) throws CoreException {
//	String current;
//	//  String outLineNumberString; never used
//	final StringBuffer lineNumberBuffer = new StringBuffer(10);
//	char ch;
//	current = output.substring(indx, brIndx);
//
//	if (current.indexOf(PARSE_WARNING_STRING) != -1 || current.indexOf(PARSE_ERROR_STRING) != -1) {
//	  final int onLine = current.indexOf("on line <b>");
//	  if (onLine != -1) {
//		lineNumberBuffer.delete(0, lineNumberBuffer.length());
//		for (int i = onLine; i < current.length(); i++) {
//		  ch = current.charAt(i);
//		  if ('0' <= ch && '9' >= ch) {
//			lineNumberBuffer.append(ch);
//		  }
//		}
//
//		final int lineNumber = Integer.parseInt(lineNumberBuffer.toString());
//
//		final Hashtable attributes = new Hashtable();
//
//		current = StringUtil.replaceAll(current, "\n", "");
//		current = StringUtil.replaceAll(current, "<b>", "");
//		current = StringUtil.replaceAll(current, "</b>", "");
//		MarkerUtilities.setMessage(attributes, current);
//
//		if (current.indexOf(PARSE_ERROR_STRING) != -1)
//		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
//		else if (current.indexOf(PARSE_WARNING_STRING) != -1)
//		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
//		else
//		  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
//		MarkerUtilities.setLineNumber(attributes, lineNumber);
//		MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);
//	  }
//	}
//  }
//

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
