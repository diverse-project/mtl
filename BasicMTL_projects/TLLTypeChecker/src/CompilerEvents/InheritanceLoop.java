/*
 * Created on 2 nov. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass;

/**
 * @author edrezen
 *
 */
public class InheritanceLoop implements CompilerMessage
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private java.util.Vector remainingUnsolved;
	private java.util.Vector getRemainingUnsolved() { return remainingUnsolved; }
	private void setRemainingUnsolved(java.util.Vector remainingUnsolved) { this.remainingUnsolved = remainingUnsolved; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public InheritanceLoop (java.util.Vector remainingUnsolved) 
	{
		setRemainingUnsolved (remainingUnsolved);
	}


	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public String getDescription() 
	{
		String result = "";
		result = "There are errors or there is an inheritance loop between following classes";
		for (int i=0; i<getRemainingUnsolved().size(); i++)
		{
			result = result + ((UserDefinedClass)getRemainingUnsolved().get(i)).getName();
		}
		return result;
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
