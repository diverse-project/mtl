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
import org.inria.mtl.markers.MTLMarkers;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;

/**
 * @author edrezen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
				CompilerMessage ReX = (CompilerMessage)getVector().elementAt(i);
				createMarkersTask (ReX, MTLMarkers.cleanMarkers);
			}
		}

		return null;
	}

	
	/** */
    public void createMarkersTask (CompilerMessage cMessage, boolean cleanMarkers) throws Exception 
	{
    	String Message=cMessage.getMessage();
    	
    	// we retrieve the file corresponding to the CompilerMessage
		IFile currFile = (IFile) MTLCommandExecutor.getFileFromCompilerMessage (cMessage);
		
		IMarker[] problems = null;
		int depth = IResource.DEPTH_INFINITE;
		
		if (currFile!=null && currFile.exists())
		{
			try {
				problems = currFile.findMarkers(IMarker.PROBLEM, true, depth);
			} 
			catch (CoreException e) {
			}
		}
		
		if (MTLMarkers.cleanMarkers && (currFile!=null))
		{
			//ED System.out.println("Enlever les MARKERS sur :"+currFile.getName());
			currFile.deleteMarkers (IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			currFile.deleteMarkers (IMarker.TASK,    true, IResource.DEPTH_INFINITE);
			MTLMarkers.cleanMarkers = false;
		}
		
		if ((Message.indexOf("ANTLRException")==0) &&(cMessage.getMessageType().compareTo("warn")==0))
		{
			String messageAll = Message.substring(Message.indexOf(" on ") + 4, Message.length() ); //$NON-NLS-1$
			String messageFile = messageAll.substring(0, messageAll.indexOf(",")); //$NON-NLS-1$
			String messageDelta = messageAll.substring(messageAll.indexOf(", ") + 2, messageAll.length()); //$NON-NLS-1$
			String messageLine = messageDelta.substring(messageDelta.indexOf("line ")+5 ,messageDelta.indexOf(":")); //$NON-NLS-1$
			String messageDesc = messageDelta.substring(messageDelta.indexOf(":",messageDelta.indexOf(":")+1)+1, messageDelta.length()); //$NON-NLS-1$
			Hashtable attributes = new Hashtable();
			MarkerUtilities.setMessage(attributes, messageDesc);
			attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
			try {
				int lineNumber =Integer.parseInt(messageLine);	//$NON-NLS-1$
				MarkerUtilities.setLineNumber(attributes, lineNumber);
			}
			catch (Exception e) {
				//ED System.out.println("Error Line number :");
			}
			
			try {
				//ED System.out.println("Créer les MARKERS sur :"+currFile.getName()+"  Message :"+cMessage.getMessage());
				MarkerUtilities.createMarker(currFile, attributes, IMarker.PROBLEM);
				MarkerUtilities.createMarker(currFile, attributes, IMarker.TASK);
				//ED System.out.println("MARKER NOUVEAU");
				//And refresh the compilation unit folder
				currFile.getParent().refreshLocal(IResource.DEPTH_ONE, null);
			}
			catch(Exception E) {
				//ED System.out.println("Pb sur les markers :"+E.getMessage());
			}
		}
		else {
			Hashtable attributes = new Hashtable();
			MarkerUtilities.setMessage(attributes, Message);
			if (cMessage.getMessageType()=="error")  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
			if (cMessage.getMessageType()=="info")   attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
			if (cMessage.getMessageType()=="debug")  attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
					
			if ((cMessage.getFileName()!=null) )
			{
				if ((cMessage.getMessageType()=="error")||(cMessage.getMessageType()=="warn"))
				{
					//ED System.out.println("Créer les MARKERS sur 2 :"+currFile.getName()+"  Message :"+cMessage.getMessage());
					MarkerUtilities.createMarker(currFile, attributes, IMarker.PROBLEM);
					MarkerUtilities.createMarker(currFile, attributes, IMarker.TASK);
					//ED System.out.println("MARKER NOUVEAU");
				}
			}	
		}
	}

}
