/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands;

/**
 * @author edrezen
 *
 * This is a Command (see [GOF]) for debugging purpose.
 * It is a Decorator (see [GOF]).
 * 
 */
public class MTLCommandDebug extends MTLCommandDelegate
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public MTLCommandDebug (MTLCommand delegate) 
	{
		super(delegate);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public Object preExecute () throws Exception 
	{
		MTLCommand.log().debug ("BEGIN BEGIN BEGIN " + getClassName());
		return null;
	}


	/** */
	public Object postExecute () throws Exception
	{
		MTLCommand.log().debug ("END END END " + getClassName());
		return null;
	}

	/** */
	private String getClassName()
	{
		String className = this.getClass().getName();
		String pkgName   = this.getClass().getPackage().getName();
		
		return className.substring (pkgName.length()+1);
	}
}
