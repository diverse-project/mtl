/*
 * Created on 27 oct. 2004
 *
 */
package org.inria.mtl.commands.edition;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.actions.OpenFileAction;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 * This command allows to open a file.
 */
public class OpenFileCommand extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private IFile file;


	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS 
	////////////////////////////////////////////////////////////////////////////////
	public IFile getFile() {
		return file;
	}
	public void setFile(IFile file) {
		this.file = file;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS 
	////////////////////////////////////////////////////////////////////////////////
	
	/**  */
	public OpenFileCommand (IFile file) 
	{
		setFile (file);
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS 
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute() throws Exception 
	{
		// we open the file
		OpenFileAction openFileAction = new OpenFileAction (MTLPlugin.getActivePage());
		openFileAction.selectionChanged (new StructuredSelection (getFile()));
		openFileAction.run();

		return null;
	}
	
}
