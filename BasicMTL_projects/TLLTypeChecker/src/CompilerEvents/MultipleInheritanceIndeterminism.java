/*
 * Created on 2 nov. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author edrezen
 *
 */
public class MultipleInheritanceIndeterminism implements CompilerMessage 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private UserDefinedClass userClass;
	private UserDefinedClass getUserClass() { return userClass; }
	private void setUserClass(UserDefinedClass userClass) { this.userClass = userClass;}

	private InheritedOpSignature operation;
	private InheritedOpSignature getOperation() { return operation; }
	private void setOperation(InheritedOpSignature operation) { this.operation = operation; }

	private QualifiedName origin;
	private QualifiedName getOrigin() {	return origin; }
	private void setOrigin(QualifiedName origin) { this.origin = origin; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public MultipleInheritanceIndeterminism (UserDefinedClass userClass, InheritedOpSignature operation, QualifiedName origin) 
	{
		setUserClass (userClass);
		setOperation (operation);
		setOrigin    (origin);
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	public String getDescription() 
	{
		String result = "";
		result = result + "Class " + getUserClass().qualifiedName + ": multiple inheritance indetermisn for method: " + getOperation().getOpName() + "\n";
		result = result + "  origin 1:" + getOperation().getTypeWhichDefineOp() + ", origin 2:" + getOrigin(); 
		result = result + "  Consider to overload it. Ex: " + getOperation().getOpName() + "(...){self.oclAsType(!inherited_type!)."+getOperation().getOpName()+"(...);}";
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
