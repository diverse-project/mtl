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
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.commands.MTLCommandExecutor;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;

/**
 * @author edrezen
 *
 */
public class CreateMarkersCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private java.util.Vector vector;
	

	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	
	public java.util.Vector getVector() {
		return vector;
	}
	public void setVector(java.util.Vector vector) {
		this.vector = vector;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public CreateMarkersCommand (java.util.Vector vector)
	{
		setVector (vector);
	}
	

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** @see org.inria.mtl.commands.MTLCommand#mainExecute()
	 */
	public Object mainExecute() throws Exception 
	{
    	MTLCommand.log().debug ("There are " + getVector().size() + " markers to be created...");

    	for (int i=0; i<getVector().size(); i++)
    	{
			if (getVector().elementAt(i) instanceof CompilerMessage)
			{ 
				createMarkersTask ((CompilerMessage)getVector().elementAt(i));
			}
		}

		return null;
	}

	
	/** */
    public void createMarkersTask (CompilerMessage cMessage) throws Exception 
	{
    	// we retrieve the file corresponding to the CompilerMessage
		IFile currentFile = (IFile) MTLCommandExecutor.getFileFromCompilerMessage (cMessage);
		
		if (currentFile != null)
		{
	    	String message = cMessage.getMessage();
	    	String type    = cMessage.getMessageType();

			if ( (message.indexOf("ANTLRException")==0) && (type.compareTo("warn")==0) )
			{
				// we set a mark according to the type of message
				Hashtable attributes = new Hashtable();

				// we extract information from this kind of compiler message
				String messageAll   = message.substring(message.indexOf(" on ") + 4, message.length() );
				String messageFile  = messageAll.substring(0, messageAll.indexOf(","));
				String messageDelta = messageAll.substring(messageAll.indexOf(", ") + 2, messageAll.length());
				String messageLine  = messageDelta.substring(messageDelta.indexOf("line ")+5 ,messageDelta.indexOf(":"));
				String messageDesc  = messageDelta.substring(messageDelta.indexOf(":",messageDelta.indexOf(":")+1)+1, messageDelta.length());

				attributes.put (IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));

				MarkerUtilities.setMessage    (attributes, messageDesc);
				MarkerUtilities.setLineNumber (attributes, Integer.parseInt(messageLine));
			
				updateMarkers (currentFile, attributes);

			}
			else if (type=="error" || type=="warn") 
			{
				// we set a mark according to the type of message
				Hashtable attributes = new Hashtable();

				MarkerUtilities.setMessage (attributes, message);
				
				if      (type=="error")  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
				else if (type=="warn")   attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
			
				updateMarkers (currentFile, attributes);
			}
		}
	}

    
    /** */
	private void updateMarkers (IResource resource, Hashtable attributes) throws CoreException 
	{
    	MTLCommand.log().debug ("We set a mark on the resource '" + resource + "' with attributes '" + attributes + "'");

    	// we set the marker and a task
		MarkerUtilities.createMarker (resource, attributes, IMarker.PROBLEM);
		MarkerUtilities.createMarker (resource, attributes, IMarker.TASK);

		// we refresh the resource since a marker has been set on it
		resource.getParent().refreshLocal(IResource.DEPTH_ONE, null);
	}
}
