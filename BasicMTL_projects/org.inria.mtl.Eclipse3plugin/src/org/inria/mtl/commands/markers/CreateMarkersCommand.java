/*
 * Created on 13 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.markers;

import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.commands.MTLCommand;

import CompilerEvents.CompilerMessage;
import CompilerEvents.FileCompilerMessageLocator;

/**
 * @author edrezen
 *
 */
public class CreateMarkersCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private CompilerMessage message;
	private CompilerMessage getMessage() { return message; }
	private void setMessage(CompilerMessage message) { this.message = message; }
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public CreateMarkersCommand (CompilerMessage message)
	{
		setMessage (message);
	} 
	

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** @see org.inria.mtl.commands.MTLCommand#mainExecute()
	 */
	public Object mainExecute() throws Exception 
	{
		Hashtable attributes = new Hashtable();

		// we set the message of the marker
		MarkerUtilities.setMessage (attributes, getMessage().getDescription());

		// we set the severity of the marker
		switch (getMessage().getSeverity())
		{
			case CompilerMessage.ERROR:
				attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
				break;
			
			case CompilerMessage.INFO:
				attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
				break;
			
			case CompilerMessage.WARNING:
				attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
				break;
			
			default:
				throw new Exception ("Impossible case in a switch statement...");
		}

		// we look for a message with a File locator
		if (getMessage().getLocator()!=null && getMessage().getLocator() instanceof FileCompilerMessageLocator)
		{
			// we retrieve the IFile from the file locator
			FileCompilerMessageLocator fileLocator = (FileCompilerMessageLocator)getMessage().getLocator();
			IFile file = MTLPlugin.getWorkspace().getRoot().getFileForLocation (new Path (fileLocator.getFileName()));

			MarkerUtilities.setLineNumber(attributes, fileLocator.getLineNumber());

			createMarkers (file, attributes);
		}
		else
		{
			// if there is no resource associated to the compiler message, we set the marker on the project
			createMarkers (MTLModel.getProject(), attributes);
		}
		
		return Boolean.TRUE;
	}

    
    /** */
	private void createMarkers (IResource resource, Hashtable attributes) throws CoreException 
	{
    	MTLCommand.log().debug ("We set a mark on the resource '" + resource + "' with attributes '" + attributes + "'");

    	// we set the marker and a task
		MarkerUtilities.createMarker (resource, attributes, IMarker.PROBLEM);
		MarkerUtilities.createMarker (resource, attributes, IMarker.TASK);
	}
}
