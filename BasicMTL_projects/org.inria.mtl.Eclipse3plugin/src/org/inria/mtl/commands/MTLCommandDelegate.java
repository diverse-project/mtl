/*
 * Created on 14 oct. 2004
 *
 */
package org.inria.mtl.commands;

/**
 * @author edrezen
 *
 * This is a Command (see [GOF]) that delegates its job to another command.
 * It can be the basis for a Proxy or a Decorator pattern (see [GOF])
 * 
 */
public class MTLCommandDelegate extends MTLCommand 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private MTLCommand delegate;
	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public MTLCommand getDelegate() {
		return delegate;
	}
	public void setDelegate(MTLCommand delegate) {
		this.delegate = delegate;
	}

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public MTLCommandDelegate (MTLCommand delegate)
	{
		setDelegate (delegate);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// DELEGATION METHOS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public Object mainExecute() throws Exception 
	{
		return getDelegate().mainExecute();
	}
	
	/** */
	public Object preExecute () throws Exception 
	{
		return getDelegate().preExecute();	
	}


	/** */
	public Object postExecute () throws Exception
	{
		return getDelegate().postExecute();	
	}
	
}
