/*
 * Created on 29 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration;


/**
 * @author edrezen
 *
 */
public class UnknownTypeInVarDeclaration extends UnknownType 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private VarDeclaration varDeclaration;
	public VarDeclaration getVarDeclaration() { return varDeclaration; }
	private void setVarDeclaration(VarDeclaration varDeclaration) { this.varDeclaration = varDeclaration; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public UnknownTypeInVarDeclaration (VarDeclaration varDeclartion) 
	{
		super (varDeclartion.getType());
		setVarDeclaration (varDeclartion);
	}


	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public String getDescription() 
	{
		return "The type '" + getType() + "' for the variable declaration '" + getVarDeclaration().getName() + "' is unknown";

	}

	/** */
	public int getSeverity() 
	{
		return CompilerMessage.ERROR;
	}

	/** */
	public CompilerMessageLocator getLocator() 
	{
		String fileName   = (String) getVarDeclaration().getProperty ("FileName").getValue();
		String lineNumber = (String) getVarDeclaration().getProperty ("LineNumber").getValue();

		return new FileCompilerMessageLocator (fileName, Integer.parseInt(lineNumber));
	}
}
