/*
 * Created on 27 oct. 2004
 *
 */
package org.inria.mtl.commands.edition;

import org.eclipse.core.resources.IFile;
import org.inria.mtl.commands.MTLCommand;


/**
 * @author edrezen
 *
 */
public class EditionCommandFactory 
{
	/** */
	public MTLCommand createOpenFileCommand (IFile file)
	{
		return new OpenFileCommand (file);
	}
	
	/** */
	public MTLCommand createGotoLineCommand (int lineNumber)
	{
		return new GotoLineCommand (lineNumber);
	}

}
