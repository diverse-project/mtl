/*
 * Created on 29 oct. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author edrezen
 * 
 * Factorization class. 
 */
abstract public class UnknownType implements CompilerMessage
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private QualifiedName type;
	public QualifiedName getType() { return type; }
	private void setType(QualifiedName type) { this.type = type; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public UnknownType (QualifiedName type)
	{
		setType (type);
	}
	
	/** */
	public String getInfoForDescription (ASTNode node)
	{
		String fileName   = (String) node.getProperty ("FileName").getValue();
		String lineNumber = (String) node.getProperty ("LineNumber").getValue();
		
		return "(file '" + fileName + "', line " + lineNumber + ")";
	}
}
