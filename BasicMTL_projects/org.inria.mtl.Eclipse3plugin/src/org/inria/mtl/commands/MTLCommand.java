/*
 * Created on 12 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands;

import java.util.Observable;
import org.inria.mtl.MTLPlugin;

/**
 * @author edrezen
 * 
 * This abstract class is a Command (in the GOF sense).
 * 
 * We use it rather the Runnable interface because the command can return a value.
 * 
 * Note that the 'execute' method is a Template Method [GOF].
 * 
 * Note that it is also an Observable (see [GOF]) since it implements Observable. 
 * The update is done after the postExecute() call.
 * 
 */
abstract public class MTLCommand extends Observable  
{
	////////////////////////////////////////////////////////////////////////////////
	// COMMAND AND TEMPLATE METHOD PATTERNS
	////////////////////////////////////////////////////////////////////////////////

	/** Template Method [GOF] for executing a command.
	 * Subclasses must implement the primitive method 'mainExecute' of the Template Method.
	 */
	/*final*/ public Object execute () throws Exception
	{
		Object e1 = preExecute();
		Object e2 = mainExecute();
		Object e3 = postExecute();
	
		// we notify the observers
		notifyObservers();
		
		return (e3!=null ? e3 : (e2!=null ? e2 : e1));
	}

	/** */
	abstract public Object mainExecute () throws Exception;

	/** */
	public Object preExecute () throws Exception 
	{
		return null;
	}


	/** */
	public Object postExecute () throws Exception
	{
		return null;
	}

	
	/** */
	public void notifyObservers () 
	{
		notifyObservers (null);
	}

	/** */
	public void notifyObservers (Object arg) 
	{
		this.setChanged();
		super.notifyObservers(arg);
		this.clearChanged();
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// LOGGING MANAGEMENT
	////////////////////////////////////////////////////////////////////////////////

	private static org.apache.log4j.Logger log4j = MTLPlugin.getLogger ("MTLCommand");
	public static org.apache.log4j.Logger log ()
	{
		return log4j;
	}
	
}
