/*
 * Created on 13 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.build;

import org.eclipse.core.resources.IFile;
import org.inria.mtl.commands.MTLCommand;
import org.inria.mtl.core.MTLCore;
import org.irisa.triskell.MT.utils.MessagesHandler.CompilerMessage;

/**
 * @author edrezen
 *
 * This command returns the IFile (if exists) corresponding to a CompilerMessage.
 * 
 */
public class GetFileFromCompilerMessageCommand extends MTLCommand  
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private CompilerMessage message;
	
	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public CompilerMessage getMessage() {
		return message;
	}
	public void setMessage(CompilerMessage message) {
		this.message = message;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public GetFileFromCompilerMessageCommand (CompilerMessage message)
	{
		setMessage (message);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** @see org.inria.mtl.commands.MTLCommand#mainExecute()
	 */
	public Object mainExecute() throws Exception 
	{
    	IFile result = null;
    	String Message = getMessage().getMessage();
		
    	if ((Message.indexOf("ANTLRException")==0) && (getMessage().getMessageType().compareTo("warn")==0))
		{
			//Erreur générée par ANTLR
			String messageAll  = Message.substring     (Message.indexOf(" on ") + 4, Message.length() ); //$NON-NLS-1$
			String messageFile = messageAll.substring  (0, messageAll.indexOf(",")); //$NON-NLS-1$
			String N           = messageFile.substring (messageFile.indexOf(MTLCore.getProject().getName())+MTLCore.getProject().getName().length()+1);
			String subPath     = getMessage().getFileName().substring (MTLCore.getProject().getLocation().toString().length()+1,getMessage().getFileName().length());

			// we determine the file
			result = MTLCore.getProject().getFile(subPath);
		}
		else 
		{ //Propriété file non nulle
			if (getMessage().getFileName()!=null)
			{
				String subPath = getMessage().getFileName().substring (
					MTLCore.getProject().getLocation().toString().length()+1,
					getMessage().getFileName().length()
				);

				// we determine the file
				result = MTLCore.getProject().getFile(subPath);
			}
			//autres cas
		}
    	
    	// we return the result
		return result;
	}
}
