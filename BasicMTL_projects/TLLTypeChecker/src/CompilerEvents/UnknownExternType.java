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
public class UnknownExternType extends UnknownType
{
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public UnknownExternType (QualifiedName type) 
	{
		super (type);
	}

	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	public String getDescription() 
	{
		return "Extern class type not found : " + getType().toString();
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
