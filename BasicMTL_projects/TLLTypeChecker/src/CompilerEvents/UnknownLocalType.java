/*
 * Created on 29 oct. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
 * @author edrezen
 *
 */
public class UnknownLocalType extends UnknownType
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public UnknownLocalType (QualifiedName type) 
	{
		super (type);
	}

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	public String getDescription() 
	{
		return "Unknown Local Type : " + getType().toString();
	}
	
	/** */
	public int getSeverity() 
	{
		return CompilerMessage.ERROR;
	}
	
	/** */
	public CompilerMessageLocator getLocator() 
	{
		return null;
	}
}
