/*
 * Created on 2 nov. 2004
 *
 */
package CompilerEvents;


/**
 * @author edrezen
 *
 */
public class UnknownInheritedParent implements CompilerMessage 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private String parentName;
	private String getParentName() { return parentName; }
	private void setParentName(String parentName) { this.parentName = parentName; }

	private String className;
	private String getClassName() { return className; }
	private void setClassName(String className) { this.className = className; }

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public UnknownInheritedParent (String parentName, String className) 
	{
		setParentName (parentName);
		setClassName  (className);
	}


	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public String getDescription() 
	{
		if (getClass() == null)
		{
			return "the inherited parent " + getParentName() + " can't be resolved !";
		}
		else
		{
			return "the inherited parent class " + getParentName() + "::" + getClassName() + " can't be resolved !";
		}
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
